fun main() {
    
    //ARRAY
    val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")
    val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")
    val solarSystem = rockPlanets + gasPlanets
    
    println(solarSystem[0])
    println(solarSystem[1])
    println(solarSystem[2])
    println(solarSystem[3])
    println(solarSystem[4])
    println(solarSystem[5])
    println(solarSystem[6])
    println(solarSystem[7])
    
    solarSystem[3] = "Little Earth"
    println(solarSystem[3])
    
    //LISTS
    val solarSystemL = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystemL.size) 
    // accesing elements through index ot get() method
    println(solarSystemL.get(3))
    println(solarSystemL.indexOf("Earth"))
    
    for (planet in solarSystemL) {
    	println(planet)
	}
    
    //MUTABLE LISTS -> can perfrom  add, remove, and update on a collection
    val solarSystemM = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    solarSystemM.add("Pluto") // adds at the end of the list
    solarSystemM.add(3, "Theia")  // adds at index 3
    println(solarSystemM[3])
    solarSystemM[3] = "Future Moon"  // update
    println(solarSystemM[3])
    solarSystemM.removeAt(9)  // remove by index
    solarSystemM.remove("Future Moon")  // remove specific element
    println(solarSystemM.contains("Pluto"))
    println("Future Moon" in solarSystemM)
    
    
    
}