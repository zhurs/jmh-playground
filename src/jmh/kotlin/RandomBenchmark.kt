package x

import jdk.internal.misc.Unsafe
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.atomic.AtomicInteger

@State(Scope.Benchmark)
open class RandomIterations {
    companion object {
        private var x: Int = 0
        @Volatile
        private var xv: Int = 0
        private var xa = AtomicInteger(0)

    }

    @Benchmark
    fun random0x1FF(bh: Blackhole) {
        bh.consume(ThreadLocalRandom.current().nextInt(0x1FF))
    }

    @Benchmark
    fun random0x200(bh: Blackhole) {
        bh.consume(ThreadLocalRandom.current().nextInt(0x200))
    }

    @Benchmark
    fun incrementStatic(bh: Blackhole) {
        bh.consume(x++)
    }

    @Benchmark
    fun incrementVolatileStatic(bh: Blackhole) {
        bh.consume(xv++)
    }

    @Benchmark
    fun incrementAtomic(bh: Blackhole) {
        bh.consume(xa.incrementAndGet())
    }
}
