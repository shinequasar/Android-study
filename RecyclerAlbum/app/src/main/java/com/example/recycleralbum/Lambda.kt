package com.example.recycleralbum

data class Circle(var r:Int)
class Song{
    companion object{
        var title:String=""
        fun show() = println(title)
    }
}

//Function definition
fun squareSum(x:Int, y:Int) : Int{
    return x*x + y*y
}

fun process(processor: (Int,Int) -> Int, a: Int, b:Int) = processor(a,b)


fun main() {
    //Lambda Function
    var sq_sum:(Int, Int) -> Int = {x: Int, y: Int -> x*x + y*y}
    println(sq_sum(3,3))

    //방법1
    var x =7
    val sq: () -> Unit={ println(x*x) }
    sq()

    //방법2
    val sq2: (Int) -> Int={it*it}
    println(sq2(7))

    var x2 = process({x,y -> x*x + y*y}, 4,4)
    println("x2 = $x2")

    x2 = process({x,y -> x*x*x + y*y*y}, 4,4)
    println("x2 = $x2")

    val c1: Circle = Circle(7)
    val c2 = Circle(7)
    println(c1.equals(c2))
    println(c1)

    val rect = object {
        var w: Int = 0
        var h: Int = 0
        fun size() = w*h
    }
    rect.w = 5; rect.h = 8; println(rect.size())
    Song.title = "Happy Song"; Song.show()
    
    //Null Safety
    var name: String? = null
    val length = if(name == null) 0 else name.length
    val length2 = name?.length ?: -1
//    val length3 = name!!.length

}