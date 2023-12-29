import java.io.File
import java.io.BufferedReader


fun calcSum(i1 : Int, j1 : Int, di1 : Int, dj1 : Int, input : MutableList<String>) : Int{
	val check = input.toMutableList()
	var sum = 0
	var q = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

	q.add(Pair(Pair(i1, j1), Pair(di1, dj1)))

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
	return sum
}


val br = File("input.txt").bufferedReader()
val input = mutableListOf<String>()

var maxSum = 0

var line = br.readLine()

while (line != null){
	input.add(line)
	line = br.readLine()
}

var sum = 0
for (c in 0 until input.size){
	sum = calcSum(c, 0, 0, 1, input)
	if (sum > maxSum) maxSum = sum
	sum = calcSum(c, input[0].length-1, 0, -1, input)
	if (sum > maxSum) maxSum = sum
}

for (r in 0 until input[0].length){
	sum = calcSum(0, r, 1, 0, input)
	if (sum > maxSum) maxSum = sum
	sum = calcSum(input.size-1, r, -1, 0, input)
	if (sum > maxSum) maxSum = sum
}

print(maxSum)
