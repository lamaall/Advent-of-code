import java.io.File
import java.io.BufferedReader

var sum = 0
val br = File("input.txt").bufferedReader()

var line = br.readLine()
while (line != null){
		
	val f = (line.find{it.isDigit()}?: '0') - '0'
	val s = (line.findLast{it.isDigit()}?: '0') - '0'

	sum += 10*f + s

	line = br.readLine()
}

print(sum)