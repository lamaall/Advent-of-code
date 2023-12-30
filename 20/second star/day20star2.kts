import java.io.File
import java.io.BufferedReader
import java.util.Queue
import java.util.LinkedList

var sum = 0
val br = File("input.txt").bufferedReader()


val flipFlops = HashMap<String, MutableList<String>>()
val flipFlopsState = HashMap<String, Int>()
val conjuctions = HashMap<String, MutableList<String>>()
val toConjuctions = HashMap<String, MutableList<Pair<String, Int>>>()
val broadcaster = mutableListOf<String>() 

var line = br.readLine()

while (line != null){
	val row = line.split(" -> ")
	if (row[0] == "broadcaster"){
		broadcaster.addAll(row[1].split(", "))
	}
	else if (row[0][0] == '%'){
		val key = row[0].substring(1)
		flipFlops[key] = row[1].split(", ").toMutableList()
		flipFlopsState[key] = 0
	}
	else if (row[0][0] == '&'){
		val key = row[0].substring(1)
		conjuctions[key] = row[1].split(", ").toMutableList()
		toConjuctions[key] = mutableListOf<Pair<String, Int>>() 
	}

	line = br.readLine()
}

flipFlops.forEach{
	entry -> for(i in entry.value){
		if (toConjuctions.contains(i)) toConjuctions[i]!!.add(Pair(entry.key, 0))
	}
}
conjuctions.forEach{
	entry -> for(i in entry.value){
		if (toConjuctions.contains(i)) toConjuctions[i]!!.add(Pair(entry.key, 0))
	}
}
for (i in broadcaster){
	if (toConjuctions.contains(i)) toConjuctions[i]!!.add(Pair("broadcaster", 0))
}

val ql = mutableListOf<Int>()
val done = mutableListOf<String>()

var i = 0
while (i < 100000){
	i += 1
	val q : Queue<List<String>> = LinkedList<List<String>>()
	for (module in broadcaster){
		q.add(listOf(module, "0", "broadcaster"))
	}
	while(!q.isEmpty()){
		val pulse = q.remove()
		val module = pulse[0]
		val type = pulse[1].toInt()
		val from = pulse[2]
		if (flipFlops.contains(module)){
			if (type == 1) continue
			flipFlopsState[module] = (1+flipFlopsState[module]!!)%2
			for (m in flipFlops[module]!!){
				q.add(listOf(m, flipFlopsState[module].toString(), module))
			}
		}
		else if(conjuctions.contains(module)){
			toConjuctions[module]!!.removeAll{ it.first == from }
			toConjuctions[module]!!.add(Pair(from, type))
			
			if (module == "ql"){
				for (pa in toConjuctions[module]!!){
					if (!done.contains(pa.first) && pa.second == 1){
						ql.add(i)
						done.add(pa.first)
					}
				}
			}
			val predicate: (Pair<String, Int>) -> Boolean = {it.second == 1}
			if (toConjuctions[module]!!.all(predicate)){
				for (m in conjuctions[module]!!){
					q.add(listOf(m, "0", module))
				}
			}
			else{
				for (m in conjuctions[module]!!){
					q.add(listOf(m, "1", module))
				}
			}
		}
	}
}

fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

var a = ql[0].toLong()
for (i in 1..3){
	a = findLCM(a, ql[i].toLong())
}
println(a)