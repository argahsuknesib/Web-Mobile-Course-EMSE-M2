fun sayHello(greeting:String, itemToGreet:String) = println("$greeting, $itemToGreet")

fun main() {
    val interestingThings = mutableListOf("Kotlin", "Java", "Javascript")
    interestingThings.add("Dogs")

    val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c", 4 to "d")
//    map.forEach { (key, value) -> println("$key -> $value")}
    map[4] = "d"
    map.put(5, "e")
    map.forEach { (key, value) -> println("$key -> $value")}


}