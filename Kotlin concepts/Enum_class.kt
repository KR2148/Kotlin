
//Generic data type
// used to toggel the datatype for a parameter 
// defined when the class is initialized inside<> before parameters
class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty:  Difficulty)
{
    
    fun print(){
        println("\"${questionText}\" is the question and \"${answer}\" is the expected answer")
    }
    
}

//enum class is to limit the set of possible values
//each value is a enum constant , accessed usinf dot operator
enum class Difficulty {
    EASY, MEDIUM, HARD
}

fun main() {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    question1.print()
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    question2.print()
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)
    question3.print()
}