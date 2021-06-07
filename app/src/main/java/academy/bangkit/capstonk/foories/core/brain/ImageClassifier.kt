package academy.bangkit.capstonk.foories.core.brain

import academy.bangkit.capstonk.foories.core.brain.Keys.DIM_BATCH_SIZE
import academy.bangkit.capstonk.foories.core.brain.Keys.DIM_IMG_SIZE_X
import academy.bangkit.capstonk.foories.core.brain.Keys.DIM_IMG_SIZE_Y
import academy.bangkit.capstonk.foories.core.brain.Keys.DIM_PIXEL_SIZE
import academy.bangkit.capstonk.foories.core.brain.Keys.INPUT_SIZE
import academy.bangkit.capstonk.foories.core.brain.Keys.LABEL_PATH
import academy.bangkit.capstonk.foories.core.brain.Keys.MAX_RESULTS
import academy.bangkit.capstonk.foories.core.brain.Keys.MODEL_PATH
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.Color
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.tensorflow.lite.Interpreter
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*
import kotlin.collections.ArrayList

class ImageClassifier constructor(assetManager: AssetManager) {

    private var interpreter: Interpreter? = null
    private var labelProb: Array<FloatArray>
    private val labels = Vector<String>()
    private val intValues by lazy { IntArray(INPUT_SIZE * INPUT_SIZE) }
    private var imgData: ByteBuffer

    init {
        try {
            val br = BufferedReader(InputStreamReader(assetManager.open(LABEL_PATH)))
            while (true) {
                val line = br.readLine() ?: break
                labels.add(line)
            }
            br.close()
        } catch (e: IOException) {
            throw RuntimeException("Problem reading label file!", e)
        }
        labelProb = Array(1) { FloatArray(labels.size) }
        imgData =
            ByteBuffer.allocateDirect(DIM_BATCH_SIZE * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE)
        imgData.order(ByteOrder.nativeOrder())
        try {
            interpreter = Interpreter(loadModelFile(assetManager, MODEL_PATH))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer =
            ByteBuffer.allocateDirect(DIM_BATCH_SIZE * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE)
        byteBuffer.order(ByteOrder.nativeOrder())

        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        for (y in 0 until DIM_IMG_SIZE_Y) {
            for (x in 0 until DIM_IMG_SIZE_X) {
                val px = bitmap.getPixel(x, y)

                val r = Color.red(px)
                val g = Color.green(px)
                val b = Color.blue(px)

                val rf = r / 255f
                val gf = g / 255f
                val bf = b / 255f
                byteBuffer.putFloat(rf)
                byteBuffer.putFloat(gf)
                byteBuffer.putFloat(bf)
            }
        }
        bitmap.recycle()

        return byteBuffer
    }

    private fun loadModelFile(assets: AssetManager, modelFilename: String): MappedByteBuffer {
        val fileDescriptor = assets.openFd(modelFilename)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun recognizeFlow(bitmap: Bitmap): Flow<List<Result>> {
        return flow {
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, true)
            val image = convertBitmapToByteBuffer(scaledBitmap)
            interpreter!!.run(image, labelProb)
            val pq = PriorityQueue<Result>(3) { lhs, rhs ->
                (rhs.confidence!!).compareTo(lhs.confidence!!)
            }
            for (i in labels.indices) {
                pq.add(
                    Result(
                        "" + i,
                        if (labels.size > i) labels[i] else "unknown",
                        labelProb[0][i],
                        null
                    )
                )
            }
            val recognitions = ArrayList<Result>()
            val recognitionsSize = pq.size.coerceAtMost(MAX_RESULTS)
            for (i in 0 until recognitionsSize) recognitions.add(pq.poll())
            emit(recognitions)
        }
    }

    fun close() {
        interpreter?.close()
    }
}