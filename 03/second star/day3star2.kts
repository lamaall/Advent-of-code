import java.io.File
import java.io.BufferedReader

fun check(lines : List<String>) : Int {
	var rowSum = 0
	for (i in 1..(lines[1].length-2)){
		if (lines[1][i] == '*') {
			rowSum += adjacent(lines, i)	
		}
	}
	return rowSum
}

fun adjacent(lines : List<String>, c : Int) : Int {
	val s = mutableSetOf<String>()
	var sum = 1
	
	for (i in 0..2){
		for (j in -1..1){
			if (lines[i][c+j].isDigit()){
				val start = findStart(lines[i], c+j)
				val end = findEnd(lines[i], c+j)
				val coords = start.toString() + ":" + end.toString() + ":" + i.toString()
				if (!s.contains(coords)){
					s.add(start.toString() + ":" + end.toString() + ":" + i.toString())
					sum *= lines[i].substring(start, end+1).toInt()
				}
			}		
		}	
	}
	if (s.size == 2){
		return sum
	}
	return 0
}

fun findStart(line : String, c : Int) : Int {
	var i = 0
	while (line[c-i].isDigit()){
		i++	
	}
	i -= 1
	return c-i
}

fun findEnd(line : String, c: Int) : Int {
	var i = 0
	while (line[c+i].isDigit()){
		i++	
	}
	i -= 1
	return c+i
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