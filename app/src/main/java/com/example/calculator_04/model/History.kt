package com.example.calculator_04.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// 생성자를 입력해서 손쉽게 ㄲ
// val 이어서 setter 는 생성되지 않고 데이터 클래스로 만들어 졌기 때문에 toEqualHashcode
// 네가지 변수가 자동으로 생성되는 코드 효과를 얻을 수 있다.

// DB의 테이블로 사용하기 위해서 클래스 위에 어노테이션 엔티티 추가
// 각 변수들도 어떠한 이름으로 DB에 저장이 되는지 명시를 해주어야 함.
@Entity
data class History(

    @PrimaryKey val uid: Int?, // uid는 Primary key 태그를 달아줄 것임.
    @ColumnInfo(name = "expression") val expression: String?,
    @ColumnInfo(name = "result") val result: String?

)
