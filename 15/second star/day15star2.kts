import java.io.File
import java.io.BufferedReader

fun hash(s : String) : Int{
	var num = 0
	for (c in s){
		num += c.toInt()
		num *= 17
		num = num%256
	}
	return num
}


var sum = 0
val br = File("input.txt").bufferedReader()

val input = mutableListOf<String>()

var line = br.readLine()

val boxes = mutableListOf<MutableList<Pair<String, Int>>>()
for (i in 0..255){
	boxes.add(mutableListOf<Pair<String, Int>>())
}


for (s in line.split(",")){
	if (s.contains("=")){
		val label = s.substring(0, s.indexOf("="))
		val box = hash(label)
		val index = boxes[box].indexOfFirst({ it.first == label })
		if (index != -1) boxes[box][index] = Pair(label, s.substring(s.indexOf("=")+1).toInt())
		else boxes[box].add(Pair(label, s.substring(s.indexOf("=")+1).toInt()))
	}
	else{
		val label = s.substring(0, s.indexOf("-"))
		val box = hash(label)
		boxes[box].removeAll({ it.first == label })
	}
}

for (i in 0..255){
	for (j in 0 until boxes[i].size){
		sum += boxes[i][j].second*(i+1)*(j+1)
	}
}


print(sum)
