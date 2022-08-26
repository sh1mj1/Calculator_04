package com.example.calculator_04

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator_04.dao.HistoryDao
import com.example.calculator_04.model.History

/*
엔티티가 무엇인지 지정을 잘 해주어야 하고, 버전을 지정해주어야 함.
버전을 지정하는 이유
앱을 업데이트 할 수록 디비가 바뀔 ㅅ ㅜ도 있따.
버전 1 -> 버전 2 로 넘어갈 때 migration을 해줌.
예를 들어 result 컬럼이 빠지ㅏ고 expression 에 전부 넣는 것으로 바뀐다하면
데이터들을 이전하면서 데이터테이블 구조도 바뀌어야 할 필요가 있다.
버전이 올라가게 되면 마이그레이션 코드를 작성을 해서 테이블 구조도 동기화되도록 해야 한다.
 */
@Database(entities = [History::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao // 앱데이터 베이스를 생성할 때 HistoryDao 을 가져올 수 있도록.
}