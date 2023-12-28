import java.io.File
import java.io.BufferedReader

var sum = 0
val br = File("input.txt").bufferedReader()

var line = br.readLine()
val colorsMax = listOf(12, 13, 14)
here@ while (line != null){
	line = line.replace(";", ",")

	val game = line.split(": ")
	val id = game[0].split(" ")[1].toInt()

	line = br.readLine()
		
	val picks = game[1].split(", ")
	for (pick in picks){
		val pickL = pick.split(" ")
		val amount = pickL[0].toInt()
		when (pickL[1]) {
			"blue" -> if (amount > colorsMax[2]) continue@here
			"red" -> if (amount > colorsMax[0]) continue@here
			"green" -> if (amount > colorsMax[1]) continue@here
		}
	}
	
	sum += id
}

print(sum)