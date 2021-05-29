package academy.bangkit.capstonk.foories.core.data.source.local.database

import academy.bangkit.capstonk.foories.core.data.source.local.entity.DateTypeConverter
import academy.bangkit.capstonk.foories.core.data.source.local.entity.FoodEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FoodEntity::class], version = 2, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class FooriesDatabase : RoomDatabase() {
    abstract fun fooriedDao(): FooriesDao
}