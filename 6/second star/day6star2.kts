import java.io.File
import java.io.BufferedReader


fun solve(t : Long, d : Long) : Long{
	var start = 0L
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
val time = line.split(":")[1].filter { it.isDigit() }.toLong()

line = br.readLine()
val distance = line.split(":")[1].filter { it.isDigit() }.toLong()

print(maxOf(0, time - 2*solve(time, distance) - 1))
