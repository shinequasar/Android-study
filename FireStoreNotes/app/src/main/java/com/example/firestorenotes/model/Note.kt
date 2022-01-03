package com.example.firestorenotes.model

class Note {
    var id: String? = null  //프로퍼티의 getter와 setter
    var title: String? = null
    var content: String? = null

    constructor() {} //디폴트 생성자가 존재해야 deserialization 가능
    constructor(id: String, title: String, content: String) {
        this.id = id
        this.title = title
        this.content = content
    }
    constructor(title: String, content: String) {
        this.title = title
        this.content = content
    }

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("content", content!!)
        return result
    }
}