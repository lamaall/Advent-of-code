import java.io.File
import java.io.BufferedReader


var sum = 0
val br = File("input.txt").bufferedReader()

var line = br.readLine()

while (line != null){
	val numbers = line.split(":")[1].split("|")
	line = br.readLine()
	
	var k = -1	

	val winning = numbers[0].split(" ").map { it.toIntOrNull() }.toSet()
	numbers[1].split(" ").forEach {
		val n = it.toIntOrNull()
		if (n != null && winning.contains(n)){
			k += 1
		} 
	}
	
	var add = 1
	if (k != -1){
		for (i in 1..k){
			add *= 2
		}
		sum += add
	}
}

print(sum)