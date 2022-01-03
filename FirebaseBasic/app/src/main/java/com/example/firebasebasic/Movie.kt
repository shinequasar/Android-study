package com.example.firebasebasic

//Firebase DB에서 영화 데이터 읽어와서 Movie 객체를 생성. Deserialization
data class Movie (
    val title: String? = null, // property에 null 설정을 해야 디폴트 생성자 만들어짐.
    val reldate: String? = null,
    val runtime: String? = null
)