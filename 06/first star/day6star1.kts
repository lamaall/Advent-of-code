import java.io.File
import java.io.BufferedReader


fun solve(t : Int, d : Int) : Int{
	var start = 0
	var end = t/2 + t%2

	while (end - start > 1){
		val mid = (end + start)/2
		if ((t-mid)*mid > d) end = mid
		else start = mid	
	}
	return start		
}

var sum = 1
val br = File("input.txt").bufferedReader()

var line = br.readLine()
val time = line.split(":")[1].split(" ").filter { it.length != 0 }.map { it.toInt() }.toList()

line = br.readLine()
val distance = line.split(":")[1].split(" ").filter { it.length != 0 }.map { it.toInt() }.toList()

for (i in 0 until time.size){
	sum *= maxOf(0, time[i] - 2*solve(time[i], distance[i]) - 1)
}
print(sum)
