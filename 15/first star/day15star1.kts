import java.io.File
import java.io.BufferedReader

var sum = 0
val br = File("input.txt").bufferedReader()

val input = mutableListOf<String>()

var line = br.readLine()


for (s in line.split(",")){
	var num = 0
	for (c in s){
		num += c.toInt()
		num *= 17
		num = num%256
	}
	sum += num
}


print(sum)
