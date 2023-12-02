import java.io.File
import java.io.BufferedReader

var sum = 0
val br = File("input.txt").bufferedReader()

var line = br.readLine()
while (line != null){
	val colorsMax = mutableListOf(0, 0, 0)
	line = line.replace(";", ",")

	val game = line.split(": ")
	val id = game[0].split(" ")[1].toInt()

	line = br.readLine()
		
	val picks = game[1].split(", ")
	for (pick in picks){
		val pickL = pick.split(" ")
		val amount = pickL[0].toInt()
		when (pickL[1]) {
			"blue" -> if (amount > colorsMax[2]) colorsMax[2] = amount
			"red" -> if (amount > colorsMax[0]) colorsMax[0] = amount
			"green" -> if (amount > colorsMax[1]) colorsMax[1] = amount
		}
	}
	
	var power = 1
	for (colorMax in colorsMax){
		power *= colorMax
	}
	sum += power
}

print(sum)