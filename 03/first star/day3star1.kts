import java.io.File
import java.io.BufferedReader

fun check(lines : List<String>) : Int {
	var rowSum = 0
	var add = false
	var n = 0
	for (i in 1..(lines[1].length-2)){
		if (lines[1][i] in '0'..'9') {
			n = 10*n + (lines[1][i] - '0')
			add = adjacent(lines, i) || add

		}
		else {
			if (add){
				rowSum += n
			}
			n = 0
			add = false
		}
	}
	if (add){
		rowSum += n
	}
	return rowSum
}

fun adjacent(lines : List<String>, c : Int) : Boolean {
	for (i in 0..2){
		for (j in -1..1){
			val c1 = c+j
			if (lines[i][c1] != '.' && !(lines[i][c1] in '0'..'9')) return true
		}
	}
	return false
}


var sum = 0
val br = File("input.txt").bufferedReader()

var line = br.readLine()
val emptyLine = ".".repeat(line.length + 2)
val lines = mutableListOf(emptyLine)
while (line != null){
	lines.add("." + line + ".")
	line = br.readLine()
	
	if (lines.size < 3) continue
	
	sum += check(lines)
	lines.removeFirst()	
}

lines.add(emptyLine)
sum += check(lines)

print(sum)