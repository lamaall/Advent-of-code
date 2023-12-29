import java.io.File
import java.io.BufferedReader
import kotlin.math.abs

var sum = 0
val br = File("input.txt").bufferedReader()

val distances = mutableListOf<Long>()
val directions = mutableListOf<Pair<Int, Int>>()

var line = br.readLine()

var index = 0
while (line != null){
	val row = line.split(" ")[2]
	when(row[7]){
		'0' -> directions.add(Pair(1,0))
		'2' -> directions.add(Pair(-1,0))
		'3' -> directions.add(Pair(0,1))
		'1' -> directions.add(Pair(0,-1))
	}
	var distance = row.substring(2, 7).toLong(radix=16)
	distances.add(distance)
	line = br.readLine()
}

var y = 0L
var x = 0L
var space = 0L

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
