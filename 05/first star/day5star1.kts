import java.io.File
import java.io.BufferedReader


var sum = 0
val br = File("input.txt").bufferedReader()

var line = br.readLine()
val input = mutableListOf<String>()

var s = ""

while (line != null){

	if (line.isEmpty()){
		input.add(s.dropLast(1))
		s = ""	
	}
	else{
		s += line + ";"
	}

	line = br.readLine()
}

if (!s.isEmpty()){
	input.add(s.dropLast(1))
}

var nums = input[0].split(": ")[1].split(" ").map { it.toLong() }.toMutableList()
var ranges = mutableListOf<List<List<Long>>>()

for (i in 1 until input.size){
	val tmp = input[i].split(";").drop(1).map { a -> a.split(" ").map{ b -> b.toLong() } }
	ranges.add(tmp)
}


for (i in 0 until ranges.size){
	for (j in 0 until nums.size){
		val num = nums[j]
		for (k in 0 until ranges[i].size){
			val range = ranges[i][k]
			val r0 = range[0]
			val r1 = range[1]
			val r2 = range[2]
			if ((num >= r1) && (num < r1 + r2)){
				nums[j] = r0 + num - r1
			}
		}
	}
}

print(nums.min())
