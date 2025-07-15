/*SINGLETON OBJECT
 * For a quiz, it would be great to have a way to keep track of the total number of questions, and the number of questions the student answered so far. 
 * You'll only need one instance of this class to exist, so instead of declaring it as a class, declare it as a singleton object.
 * Things inside this object can be directly accessed using dot operator
 * */

// declaring singleton objects seperatly
/*object StudentProgress {
    var total: Int = 10
    var answered: Int = 3
}*/

fun main() {
//     println("${StudentProgress.answered} of ${StudentProgress.total} answered.")
//     println("${Quiz.answered} of ${Quiz.total} answered.")
    
    println(Quiz.progressText)
}


// COMPANION OBJECT
//  A companion object allows you to access its properties and methods from inside the class, if the object's properties and methods belong to that class, allowing for more concise syntax.
class Quiz {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }

}
class Question<T>(
    val question : String,
    val answer :T,
    val difficulty : Difficulty
)
enum class Difficulty{
    EASY, MEDIUM, HARD
}

// ONe methid that can be used to Extend classes with new properties and methods
val Quiz.StudentProgress.progressText: String
get() = "${answered} of ${total} answered"
