import java.io.File
import java.io.BufferedReader


fun check(s : String) : Int {
	l = mutableSetOf<Char>()
	for (c in s){
		l.add(c)
	}
	
	

}

var sum = 0
val br = File("input.txt").bufferedReader()

var line = br.readLine()

while (line != null){
	
	val tmp = line.split(" ")
	var s = tmp[0].replace('A', 'z'-'a').replace('K', 'z'-'b').replace('Q', 'z'-'c').replace('J', 'z'-'d').replace('T', 'z'-'e')
	var b = tmp[1].toInt()


	line = br.readLine()
}

print(nums.min())
