
//Generic data type
// used to toggel the datatype for a parameter 
// defined when the class is initialized inside<> before parameters
class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: String)
{
    
    fun print(){
        println("\"${questionText}\" is the question and \"${answer}\" is the expected answer")
    }
    
}


fun main() {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", "medium")
    question1.print()
    val question2 = Question<Boolean>("The sky is green. True or false", false, "easy")
    question2.print()
    val question3 = Question<Int>("How many days are there between full moons?", 28, "hard")
    question3.print()
}