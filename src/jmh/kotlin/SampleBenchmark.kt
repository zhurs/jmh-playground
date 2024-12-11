package x

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.time.Instant
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

@State(Scope.Benchmark)
open class SampleBenchmark {
    val d1 = Instant.now().atOffset(ZoneOffset.UTC)
    val ld = d1.toLocalDate()

    val d2 = Instant.now().minus(10, ChronoUnit.DAYS).atOffset(ZoneOffset.UTC)

    @Benchmark
    fun compareDayOfYear(bh: Blackhole) {
        bh.consume(d1.dayOfYear == d2.dayOfYear)
    }

    @Benchmark
    fun compareLocalDate(bh: Blackhole) {
        bh.consume(d1.toLocalDate() == d2.toLocalDate())
    }

    @Benchmark
    fun intHash(bh: Blackhole) {
        bh.consume(234324.hashCode())
    }

    @Benchmark
    fun localDateHash(bh: Blackhole) {
        bh.consume(ld.hashCode())
    }
}