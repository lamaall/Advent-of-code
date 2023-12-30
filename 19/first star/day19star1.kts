import java.io.File
import java.io.BufferedReader


fun workFlow(part : List<Int>, rule : String) : String{
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
			val res = when(row[0][1]){
				'>' -> part[at] > v
				'<' -> part[at] < v
				else -> false
			}
			if (res) return to
		}
		else return i
	}

	return "R"
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

line = br.readLine()
while (line != null){
	val part = mutableListOf<Int>()
	for (i in line.replace("}", "").split(",")){
		part.add(i.split("=")[1].toInt())
	}
	var rule = "in"
	while (rule != "A" && rule != "R"){
		rule = workFlow(part, mapOfRules[rule]!!)
	}

	if (rule == "A"){
		sum += part.sum()
	}

	line = br.readLine()
}

print(sum)