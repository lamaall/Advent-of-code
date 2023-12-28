import java.io.File
import java.io.BufferedReader


fun handList(s : String) : List<Int>{
	val hand = mutableListOf<Int>()
	
	for (c in s){
		when(c){
			'T' -> hand.add(10)
			'J' -> hand.add(1)
			'Q' -> hand.add(12)
			'K' -> hand.add(13)
			'A' -> hand.add(14)
			else -> hand.add(c - '0')
		}	
	}

	return hand
}


fun customSort(hand : List<Int>) : Long{
	var type = 0
	var two = 0
	var add = 0
	val predicate: (Int) -> Boolean = {it == 1}
	var jokers = hand.count(predicate)

	val l = hand.sorted()
	
	var current = 1
	for (i in 1 until l.size){
		if (l[i] == 1) continue
		if (l[i] == l[i-1]) current += 1
		else current = 1
		if (current >= type){
			type = current
		}
		if (current == 2) two += 1
	}
	
	type += jokers
	
	if (two == 2) add = 5
	return 0L + hand[4] + hand[3]*100 + hand[2]*10000 + hand[1]*1000000 + hand[0]*100000000 + (type*10 + add)*10000000000
}


var sum = 0L
val br = File("input.txt").bufferedReader()

var line = br.readLine()
var hands = mutableListOf<Pair<List<Int>, Long>>()

while (line != null){
	val input = line.split(" ")
	hands.add(Pair(handList(input[0]), input[1].toLong()))
	
	line = br.readLine()
}

var rank = 1
for (i in hands.sortedBy { customSort(it.first) }){
	sum += i.second*rank
	rank += 1
}

print(sum)