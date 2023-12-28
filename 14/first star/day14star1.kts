import java.io.File
import java.io.BufferedReader

var sum = 0
val br = File("input.txt").bufferedReader()

val input = mutableListOf<String>()

var line = br.readLine()
while (line != null){
	input.add(line)
	line = br.readLine()
}


for (j in 0 until input[0].length){
	var add = input.size
	for (i in 0 until input.size){
		if (input[i][j] == 'O'){
			sum += add
			add -= 1
		}
		else if (input[i][j] == '#'){
			add = input.size - i - 1	
		}
	}
}


print(sum)
