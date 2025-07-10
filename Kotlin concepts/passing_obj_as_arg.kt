fun main() {    
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)
    
    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
       // Fill in code
        if (referrer == null)
        	println("Name: ${name}\n Age: ${age}\n Likes to ${hobby}. Doesn't have a referrer.")
        else
        	println("Name: ${name}\n Age: ${age}\n Has a referrer named ${referrer.name}, who likes to ${referrer.hobby}.")
    }
}