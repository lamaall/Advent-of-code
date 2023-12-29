import java.io.File
import java.io.BufferedReader
import java.util.PriorityQueue

var sum = 0
val br = File("input.txt").bufferedReader()

val input = mutableListOf<String>()
val check = mutableListOf<MutableList<MutableList<Int>>>()

var line = br.readLine()

var index = 0
while (line != null){
	input.add(line)
	check.add(mutableListOf<MutableList<Int>>())
	for (c in line){
		check[index].add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
	}
	line = br.readLine()
	index += 1
}

val q =  PriorityQueue<Pair<Int, List<Int>>>(compareBy { it.first })


val maxi = input.size-1
val maxj = input[0].length-1

q.add(Pair(0, listOf(0,0,1,0,1)))
q.add(Pair(0, listOf(0,0,0,1,1)))

while (true){
	val road = q.remove()
	val l = road.second[4]
	if (l > 3) continue
	var i = road.second[0]
	var j = road.second[1]
	val di = road.second[2]
	val dj = road.second[3]
	if (check[i][j][(l-1)*4+0] == 1 && di == 1) continue
	if (check[i][j][(l-1)*4+1] == 1 && di == -1) continue
	if (check[i][j][(l-1)*4+2] == 1 && dj == 1) continue
	if (check[i][j][(l-1)*4+3] == 1 && dj == -1) continue
	when(di){
		1 -> check[i][j][(l-1)*4+0] = 1
		-1 -> check[i][j][(l-1)*4+1] = 1
	}
	when(dj){
		1 -> check[i][j][(l-1)*4+2] = 1
		-1 -> check[i][j][(l-1)*4+3] = 1
	}

	val d = road.first

	if (i == maxi && j == maxj){
		print(d)
		break
	}
	
	i += di
	j += dj

	if (i < 0 || i >= input.size) continue
	if (j < 0 || j >= input[0].length) continue

	q.add(Pair(input[i][j]-'0'+d, listOf(i,j,di,dj,l+1)))
	q.add(Pair(input[i][j]-'0'+d, listOf(i,j,(di+1)%2,(dj+1)%2,1)))
	q.add(Pair(input[i][j]-'0'+d, listOf(i,j,-((di+1)%2),-((dj+1)%2),1)))
}
















