class Song(val title:String, val artist:String,val year_published:Int,val play_count:Int){
    
//     fun isPopular()
//     {
//         if (play_count>1000)
//         {
//             println("Popular")
//         }
//     }
    val isPopular: Boolean
        get() = play_count >= 1000
    
    fun print(){
        println("${title}, performed by ${artist}, was released in ${year_published}.")
        
    }
    
}
fun main(){
    val obj = Song("name","singer",2003,10000)
//     obj.isPopular()
	println(obj.isPopular)
    obj.print()
}