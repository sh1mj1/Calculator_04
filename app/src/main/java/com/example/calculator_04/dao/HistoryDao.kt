package com.example.calculator_04.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.calculator_04.model.History

/*
여기서 Room에 있는 데이터들을 어떻게 관리할 것인지 정해줌.
일단 위에서 세가지 기능만 사용할 것임.
 */
@Dao
interface HistoryDao {

    // TODO:  데이터 클래스 History 을 굳이 대분자로 적지 않아도 되는 듯? 나중에 바꿔보자
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM history")
    fun deleteAll()

    @Delete
    fun delete(history: History)

// WHERE 문에 조건으로 걸리면서 해당 조건에 맞는 것들을 LIMIT 숫자 만큼 반환됨.
    @Query("SELECT * FROM history WHERE result LIKE :result LIMIT 1")
    fun findByResult(result: String): List<History>

}

