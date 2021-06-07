package academy.bangkit.capstonk.foories.core.data.source.local.database

import academy.bangkit.capstonk.foories.core.data.source.local.entity.FoodEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FooriesDao {
    @Insert
    fun insertFood(foodEntity: FoodEntity)

    @Query("select * from foods where addedAt = date('now')")
    fun getTodayFoods(): LiveData<List<FoodEntity>>

    @Query("select * from foods where addedAt between date('now' , '-7 day') and date('now')")
    fun getAWeekFood(): LiveData<List<FoodEntity>>

    @Query("select * from foods where addedAt between date('now' , '-30 day') and date('now')")
    fun getAMonthFood(): LiveData<List<FoodEntity>>

    @Query("select sum(calories) from foods where addedAt = date('now')")
    fun getTodayTotalCalories(): LiveData<Double>
}