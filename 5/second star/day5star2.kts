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
	var newNums = mutableListOf<Long>()
	for (k in 0 until ranges[i].size){
		val range = ranges[i][k]
		val r0 = range[0]
		val r1 = range[1]
		val r2 = range[2]
		val left = mutableListOf<Long>()
		while (nums.size > 0){
			val start = nums.removeFirst()
			val length = nums.removeFirst()
			if (start + length <= r1 || start >= r1+r2) {
				left.add(start)
				left.add(length)
			}
			else {
				val m1 = maxOf(start, r1)
				val m2 = minOf(r1+r2, start+length)			
				newNums.add(m1-r1+r0)
				newNums.add(m2-m1)
				if (m1 != start){
					nums.add(start)
					nums.add(m1-start)	
				}
				if (m2 != start+length){
					nums.add(m2)
					nums.add(start+length-m2)
				}
			}
		}
		while (left.size > 0){
			val start = left.removeFirst()
			val length = left.removeFirst()
			nums.add(start)
			nums.add(length)
		}
	}
	nums.addAll(newNums)
}
var minimum = nums[0]
for (j in 0 until nums.size){
	if (j % 2 == 1) continue
	if (nums[j] < minimum) minimum = nums[j]
}

print(minimum)
