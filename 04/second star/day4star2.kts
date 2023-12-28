import java.io.File
import java.io.BufferedReader


var sum = 0
val br = File("input.txt").bufferedReader()

var line = br.readLine()
val l = mutableListOf<Int>(1)

var i = 0

while (line != null){
	val numbers = line.split(":")[1].split("|")
	line = br.readLine()
	
	var k = 0

	val winning = numbers[0].split(" ").map { it.toIntOrNull() }.toSet()
	numbers[1].split(" ").forEach {
		val n = it.toIntOrNull()
		if (n != null && winning.contains(n)){
			k += 1
		} 
	}
	
	if (i < l.size){
		l.add(1)
	}

	for (j in 1..k){
		if (i + j < l.size) {
			l[i+j] += l[i]
		}
		else {
			l.add(1+l[i])
		}
	}
	i += 1
}

print(l.subList(0,i).sum())