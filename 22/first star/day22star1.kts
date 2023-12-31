import java.io.File
import java.io.BufferedReader


fun inRange(a : Int, r1 : Int, r2 : Int) : Boolean{
	return a >= r1 && a <= r2
}

var sum = 0
val br = File("input.txt").bufferedReader()

val bricks = mutableListOf<MutableList<Int>>()

var line = br.readLine()

while (line != null){
	line = line.replace("~",",")
	bricks.add(line.split(",").map{it.toInt()}.toMutableList())

	line = br.readLine()
}

bricks.sortBy { it[2] }

val fallenBricks = mutableListOf<MutableList<Int>>()

for (brick in bricks){
	val a = brick.toMutableList()
	val currentSize = fallenBricks.size
	var added = false
	a.add(0)
	for (i in 0 until currentSize){
		val b = fallenBricks[currentSize-i-1]
		if (b[5] >= a[2]) continue
		if ((inRange(a[0], b[0], b[3]) || inRange(a[3], b[0], b[3]) || inRange(b[0], a[0], a[3]) || inRange(b[3], a[0], a[3]))
			&& (inRange(a[1], b[1], b[4]) || inRange(a[4], b[1], b[4]) || inRange(b[1], a[1], a[4]) || inRange(b[4], a[1], a[4]))){
			a[2] = b[5]+1
			a[5] = brick[5]-brick[2]+b[5]+1
			added = true
			break
		}
	}
	
	if (!added){
		a[2] = 1
		a[5] = brick[5]-brick[2]+1
	}
	fallenBricks.add(a)
	fallenBricks.sortBy { it[5] }
}

val currentSize = fallenBricks.size
for (i in 0 until currentSize){
	val a = fallenBricks[currentSize-i-1]
	val way = mutableListOf<MutableList<Int>>()
	for (j in i+1 until currentSize){
		val b = fallenBricks[currentSize-j-1]
		if (way.size >= 1){
			if (b[5] != way[0][5]) continue
		}
		if (b[5] >= a[2]) continue
		if ((inRange(a[0], b[0], b[3]) || inRange(a[3], b[0], b[3]) || inRange(b[0], a[0], a[3]) || inRange(b[3], a[0], a[3]))
			&& (inRange(a[1], b[1], b[4]) || inRange(a[4], b[1], b[4]) || inRange(b[1], a[1], a[4]) || inRange(b[4], a[1], a[4]))){
			way.add(b)
		}
	}
	if (way.size == 1) way[0][6] += 1
}


fallenBricks.forEach{
	if (it[6] == 0) sum += 1
}

print(sum)


