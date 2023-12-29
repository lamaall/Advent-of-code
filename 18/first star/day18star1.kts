import java.io.File
import java.io.BufferedReader
import kotlin.math.abs

var sum = 0
val br = File("input.txt").bufferedReader()

val distances = mutableListOf<Int>()
val directions = mutableListOf<Pair<Int, Int>>()

var line = br.readLine()

var index = 0
while (line != null){
	val row = line.split(" ")
	distances.add(row[1].toInt())
	when(row[0]){
		"R" -> directions.add(Pair(1,0))
		"L" -> directions.add(Pair(-1,0))
		"U" -> directions.add(Pair(0,1))
		"D" -> directions.add(Pair(0,-1))
	}
	line = br.readLine()
}

var y = 0
var x = 0
var space = 0

for (i in 0 until distances.size){
	val d = distances[i]
	val dx = directions[i].first
	val dy = directions[i].second
	if (abs(dx) == 1){
		space += dx*d*y
		x += dx*d
	}
	else{
		y += dy*d
	}
	
}

print(abs(space)+distances.sum()/2+1)
