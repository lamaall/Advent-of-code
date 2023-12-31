import java.io.File
import java.io.BufferedReader
import java.util.Queue
import java.util.LinkedList
import kotlin.math.abs

var sum = 0
val br = File("input.txt").bufferedReader()

val garden = mutableListOf<String>()
val visited = mutableListOf<String>()

var line = br.readLine()

var i = -1
var j = -1
while (line != null){
	garden.add(line)
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
q.add(listOf(i, j, 64))

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
	sum += line.count({it == 'O'})
}
print(sum)


