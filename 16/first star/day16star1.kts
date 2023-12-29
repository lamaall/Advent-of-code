import java.io.File
import java.io.BufferedReader

var sum = 0
val br = File("input.txt").bufferedReader()

val input = mutableListOf<String>()
val check = mutableListOf<String>()

var line = br.readLine()

while (line != null){
	input.add(line)
	check.add(line)
	line = br.readLine()
}


var q = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

q.add(Pair(Pair(0, 0), Pair(0, 1)))

while (!q.isEmpty()){
	val beam = q.first()
	q = q.drop(1).toMutableList()
	var i = beam.first.first
	var j = beam.first.second
	var di = beam.second.first
	var dj = beam.second.second

	if (i >= input.size || i < 0) continue
	if (j >= input[0].length || j < 0) continue
	
	if (input[i][j] == '.' || ((di == 1 || di == -1) && input[i][j] == '|') || ((dj == 1 || dj == -1) && input[i][j] == '-')){
		q.add(Pair(Pair(i+di, j+dj), Pair(di, dj)))
	}
	else if (input[i][j] == '\\'){
		q.add(Pair(Pair(i+dj, j+di), Pair(dj, di)))
	}
	else if (input[i][j] == '/'){
		q.add(Pair(Pair(i-dj, j-di), Pair(-dj, -di)))
	}
	else if (input[i][j] == '|'){
		if (check[i][j] == '#') continue
		q.add(Pair(Pair(i+1, j), Pair(1, 0)))
		q.add(Pair(Pair(i-1, j), Pair(-1, 0)))
	}
	else if (input[i][j] == '-'){
		if (check[i][j] == '#') continue
		q.add(Pair(Pair(i, j+1), Pair(0, 1)))
		q.add(Pair(Pair(i, j-1), Pair(0, -1)))
	}
	check[i] = check[i].substring(0, j)+'#'+check[i].substring(j+1)
}

for (s in check){
	sum += s.count({it == '#'})
}

print(sum)
