import java.io.File
import java.io.BufferedReader


fun workFlow(partMin : MutableList<Int>, partMax : MutableList<Int>, key : String, mapOfRules : HashMap<String, String>) : Long{
	var sum = 0L

	if (key == "A"){
		sum += 1
		for (i in 0..3){
			sum *= (partMax[i]-partMin[i]+1)
		}
		return sum
	}
	else if (key == "R") return 0L

	val rule = "" + mapOfRules[key]
	
	for (i in rule.split(",")){
		if (i.contains(":")){
			val row = i.split(":")
			val to = row[1]
			val at = when(row[0][0]){
				'x' -> 0
				'm' -> 1
				'a' -> 2
				's' -> 3
				else -> -1
			}
			val v = row[0].substring(2).toInt()
			
			if (row[0][1] == '<'){
				if (partMin[at] > v) continue
				else if (partMax[at] > v){
					val newPartMax = partMax.toMutableList()
					newPartMax[at] = v-1
					sum += workFlow(partMin.toMutableList(), newPartMax, to, mapOfRules)
					partMin[at] = v
				}
				else return sum + workFlow(partMin.toMutableList(), partMax.toMutableList(), to, mapOfRules)
			}
			
			else if (row[0][1] == '>'){
				if (partMax[at] < v) continue
				else if (partMin[at] < v){
					val newPartMin = partMin.toMutableList()
					newPartMin[at] = v+1
					sum += workFlow(newPartMin, partMax.toMutableList(), to, mapOfRules)
					partMax[at] = v
				}
				else return sum + workFlow(partMin.toMutableList(), partMax.toMutableList(), to, mapOfRules)
			}
		}
		else return sum + workFlow(partMin.toMutableList(), partMax.toMutableList(), i, mapOfRules)
	}

	return sum
}


var sum = 0
val br = File("input.txt").bufferedReader()

val mapOfRules = HashMap<String, String>()

var line = br.readLine()

while (!line.isEmpty()){
	val row = line.split("{")
	val key = row[0]
	val rules = row[1].substring(0, row[1].length-1)

	mapOfRules[key] = rules
	
	line = br.readLine()
}

print(workFlow(mutableListOf(1,1,1,1), mutableListOf(4000,4000,4000,4000), "in", mapOfRules))