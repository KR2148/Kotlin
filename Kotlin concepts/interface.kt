fun main() { 
    // way to call properties of interface when overrided in a class
     Quiz().printProgressBar()
     println(Quiz().progressText)
}

// defining an interface
interface ProgressPrintable {
    val progressText: String
     fun printProgressBar()
}

class Quiz : ProgressPrintable {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
    
//     implementation of property present in interface
    override val progressText: String
    get() = "${answered} of ${total} answered"
    
    override fun printProgressBar() {
    repeat(Quiz.answered) { print("▓") }
    repeat(Quiz.total - Quiz.answered) { print("▒") }
    println()
    println(progressText)
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


