package x

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.util.UUID
import kotlin.random.Random

@State(Scope.Benchmark)
open class ListIterations {
    val data = (1..10_000).map { Record() }

    @Benchmark
    fun severalIterations(bh: Blackhole) {
        bh.consume(data.map { it.int1 }.toTypedArray())
        bh.consume(data.map { it.int2 }.toTypedArray())
        bh.consume(data.map { it.int3 }.toTypedArray())
        bh.consume(data.map { it.int4 }.toTypedArray())
        bh.consume(data.map { it.str1 }.toTypedArray())
        bh.consume(data.map { it.str2 }.toTypedArray())
        bh.consume(data.map { it.str3 }.toTypedArray())
        bh.consume(data.map { it.str4 }.toTypedArray())
    }

    @Benchmark
    fun severalIterationsWoArrays(bh: Blackhole) {
        bh.consume(data.map { it.int1 })
        bh.consume(data.map { it.int2 })
        bh.consume(data.map { it.int3 })
        bh.consume(data.map { it.int4 })
        bh.consume(data.map { it.str1 })
        bh.consume(data.map { it.str2 })
        bh.consume(data.map { it.str3 })
        bh.consume(data.map { it.str4 })
    }

    @Benchmark
    fun oneIterationArrays(bh: Blackhole) {
        val ai1 = arrayOfNulls<Int>(data.size)
        val ai2 = arrayOfNulls<Int>(data.size)
        val ai3 = arrayOfNulls<Int>(data.size)
        val ai4 = arrayOfNulls<Int>(data.size)
        val si1 = arrayOfNulls<String>(data.size)
        val si2 = arrayOfNulls<String>(data.size)
        val si3 = arrayOfNulls<String>(data.size)
        val si4 = arrayOfNulls<String>(data.size)
        for (i in data.indices) {
            ai1[i] = data[i].int1
            ai2[i] = data[i].int2
            ai3[i] = data[i].int3
            ai4[i] = data[i].int4
            si1[i] = data[i].str1
            si2[i] = data[i].str2
            si3[i] = data[i].str3
            si4[i] = data[i].str4
        }
        bh.consume(ai1)
        bh.consume(ai2)
        bh.consume(ai3)
        bh.consume(ai4)
        bh.consume(si1)
        bh.consume(si2)
        bh.consume(si3)
        bh.consume(si4)
    }

    @Benchmark
    fun oneIterationArraysWithInit(bh: Blackhole) {
        bh.consume(Array(data.size) { data[it].int1 })
        bh.consume(Array(data.size) { data[it].int2 })
        bh.consume(Array(data.size) { data[it].int3 })
        bh.consume(Array(data.size) { data[it].int4 })
        bh.consume(Array(data.size) { data[it].str1 })
        bh.consume(Array(data.size) { data[it].str2 })
        bh.consume(Array(data.size) { data[it].str3 })
        bh.consume(Array(data.size) { data[it].str4 })
    }

    @Benchmark
    fun oneIterationArraysWithFun(bh: Blackhole) {
        bh.consume(data.myToArray { it.int1 })
        bh.consume(data.myToArray { it.int2 })
        bh.consume(data.myToArray { it.int3 })
        bh.consume(data.myToArray { it.int4 })
        bh.consume(data.myToArray { it.str1 })
        bh.consume(data.myToArray { it.str2 })
        bh.consume(data.myToArray { it.str3 })
        bh.consume(data.myToArray { it.str4 })
    }

    private inline fun <T, reified R> List<T>.myToArray(f: (T) -> R): Array<R> =
        Array(size) { f(this[it]) }

    @Benchmark
    fun oneIterationMutableLists(bh: Blackhole) {
        val ai1 = mutableListOf<Int>()
        val ai2 = mutableListOf<Int>()
        val ai3 = mutableListOf<Int>()
        val ai4 = mutableListOf<Int>()
        val si1 = mutableListOf<String>()
        val si2 = mutableListOf<String>()
        val si3 = mutableListOf<String>()
        val si4 = mutableListOf<String>()
        for (i in data.indices) {
            ai1.add(data[i].int1)
            ai2.add(data[i].int2)
            ai3.add(data[i].int3)
            ai4.add(data[i].int4)
            si1.add(data[i].str1)
            si2.add(data[i].str2)
            si3.add(data[i].str3)
            si4.add(data[i].str4)
        }
        bh.consume(ai1)
        bh.consume(ai2)
        bh.consume(ai3)
        bh.consume(ai4)
        bh.consume(si1)
        bh.consume(si2)
        bh.consume(si3)
        bh.consume(si4)
    }
}

data class Record(
    val int1: Int = Random.nextInt(),
    val int2: Int = Random.nextInt(),
    val int3: Int = Random.nextInt(),
    val int4: Int = Random.nextInt(),
    val str1: String = UUID.randomUUID().toString(),
    val str2: String = UUID.randomUUID().toString(),
    val str3: String = UUID.randomUUID().toString(),
    val str4: String = UUID.randomUUID().toString(),
)