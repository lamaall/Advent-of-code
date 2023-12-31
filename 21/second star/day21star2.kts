import java.io.File
import java.io.BufferedReader
import java.util.Queue
import java.util.LinkedList
import kotlin.math.abs

var even = 0
var odd = 0
val br = File("input.txt").bufferedReader()

val input = mutableListOf<String>()
val visited = mutableListOf<String>()

var line = br.readLine()

var i = -1
var j = -1
while (line != null){
	input.add(line)
	if (j == -1) i += 1
	if (line.contains("S")){
		for (c in 0 until line.length){
			if (line[c] == 'S') j = c
		}
		line = line.substring(0, j) + '.' + line.substring(j+1)
	}
	visited.add(line)
	
	line = br.readLine()
}

val q : Queue<List<Int>> = LinkedList<List<Int>>()
q.add(listOf(i, j, input.size))

while (!q.isEmpty()){
	val at = q.remove()
	i = at[0]
	j = at[1]
	val left = at[2]
	if (i < 0 || i >= visited.size) continue
	if (j < 0 || j >= visited[0].length) continue
	if (visited[i][j] != '.' || left == -1) continue
	if (left%2 == 0) visited[i] = visited[i].substring(0, j) + 'O' + visited[i].substring(j+1)
	else visited[i] = visited[i].substring(0, j) + 'X' + visited[i].substring(j+1)
	for (di in -1..1){
		for (dj in -1..1){
			if (abs(di) + abs(dj) == 1) q.add(listOf(i+di, j+dj, left-1))
		}
	}
}

for (line in visited){
	even += line.count({it == 'O'})
	odd += line.count({it == 'X'})
}

var total = 0L

var moves = 26501300L
var perSquare = input.size

var sq = moves/perSquare-1

if (sq % 2 == 0L){
	for (i in -sq..sq){
		total += ((sq - abs(i)) + 1)*even
		total += (sq - abs(i))*odd
	}
}
else{
	for (i in -sq..sq){
		total += ((sq - abs(i)) + 1)*odd
		total += (sq - abs(i))*even
	}
}

//used star1 code
var even65 = 3947
var odd65 = 3841

total += sq*(3*even+even65)
total += (sq+1)*(odd-odd65)
total += 2*(even65+even)

println(total)








