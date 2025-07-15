fun main() { 
    // way to call properties of interface when overrided in a class
     Quiz().printProgressBar()
     println(Quiz().progressText)
     
     val quiz = Quiz()  // object of Quiz class
     quiz.printQuiz()   
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
    
    // using let() scope fucntion to avoid the duplication of use of object name while accessing its properties
    fun printQuiz() {
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
	}

}
class Question<T>(
    val questionText : String,
    val answer :T,
    val difficulty : Difficulty
)
enum class Difficulty{
    EASY, MEDIUM, HARD
}


