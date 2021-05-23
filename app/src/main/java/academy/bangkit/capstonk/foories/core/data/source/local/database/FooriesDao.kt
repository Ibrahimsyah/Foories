package academy.bangkit.capstonk.foories.core.data.source.local.database

import academy.bangkit.capstonk.foories.core.data.source.local.entity.Food
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FooriesDao {
    @Insert
    fun insertFood(food: Food)

    @Query("select * from foods where addedAt = date('now')")
    fun getTodayFood(): LiveData<List<Food>>
}