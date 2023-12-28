import java.io.File
import java.io.BufferedReader

fun north(input : MutableList<String>) : MutableList<String>{
	var change = true
	while (change){
		change = false
		for (i in 0 until input.size-1){
			for (j in 0 until input[i].length){
				if (input[i][j] == '.' && input[i+1][j] == 'O'){
					input[i] = input[i].substring(0, j) + 'O' + input[i].substring(j+1)
					input[i+1] = input[i+1].substring(0, j) + '.' + input[i+1].substring(j+1)
					change = true
				}
			}
		}
	}
	return input
}

fun west(input : MutableList<String>) : MutableList<String>{
	var change = true
	while (change){
		change = false
		for (j in 0 until input[0].length-1){
			for (i in 0 until input.size){
				if (input[i][j] == '.' && input[i][j+1] == 'O'){
					input[i] = input[i].substring(0, j) + 'O' + '.' + input[i].substring(j+2)
					change = true
				}
			}
		}
	}
	return input
}

fun south(input : MutableList<String>) : MutableList<String>{
	var change = true
	while (change){
		change = false
		for (i in 1 until input.size){
			for (j in 0 until input[i].length){
				if (input[input.size-i][j] == '.' && input[input.size-i-1][j] == 'O'){
					input[input.size-i] = input[input.size-i].substring(0, j) + 'O' + input[input.size-i].substring(j+1)
					input[input.size-i-1] = input[input.size-i-1].substring(0, j) + '.' + input[input.size-i-1].substring(j+1)
					change = true
				}
			}
		}
	}
	return input
}
fun east(input : MutableList<String>) : MutableList<String>{
	var change = true
	while (change){
		change = false
		for (j in 1 until input[0].length){
			for (i in 0 until input.size){
				if (input[i][input[0].length-j] == '.' && input[i][input[0].length-j-1] == 'O'){
					input[i] = input[i].substring(0, input[0].length-j-1) + '.' + 'O' + input[i].substring(input[0].length-j+1)
					change = true
				}
			}
		}
	}
	return input
}

fun cycle(input : MutableList<String>) : MutableList<String>{
	return east(south(west(north(input))))
}


fun calcSum(input : List<String>) : Long{
	var sum = 0L
	for (i in 0 until input.size){
		for (j in 0 until input[i].length){
			if (input[i][j] == 'O') sum += input.size-i
		}
	}
	return sum
}


var sum = 0
val br = File("input.txt").bufferedReader()

var input = mutableListOf<String>()

var line = br.readLine()
while (line != null){
	input.add(line)
	line = br.readLine()
}


var inputCheck = mutableListOf<String>()
inputCheck.add(input.joinToString("\n"))

var input1 = input.toMutableList()

var check1 = false
var check2 = false
var calc1 = 1
var calc2 = 0

while (!check2){	
	input1 = cycle(input1.toMutableList())
	if (!check1){
		if (inputCheck.contains(input1.joinToString("\n"))){
			check1 = true
			input = input1
		}
		else {
			inputCheck.add(input1.joinToString("\n"))
			calc1 += 1
		}
	}
	else if (!check2){
		calc2 += 1
		if (input1 == input) check2 = true
	}
}

for (i in 0 until (1000000000-calc1) % calc2){
	input = cycle(input.toMutableList())
}

println(calcSum(input))
