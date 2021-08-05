package rationals
import org.w3c.dom.ranges.Range
import java.math.BigInteger
import java.util.NoSuchElementException

class Rational (numerator:BigInteger,denominator:BigInteger){
    val rational:Pair<BigInteger,BigInteger> = Pair(if (numerator<0.toBigInteger()) -numerator else numerator,
        if (denominator< 0.toBigInteger()) -denominator else denominator)
    val normalized: Pair<BigInteger,BigInteger> = normalizeIt()
    var minussign: String
    init {
        this.minussign = if((numerator > "0".toBigInteger() && denominator > "0".toBigInteger())
            or (numerator < "0".toBigInteger() && denominator < "0".toBigInteger() ) ) "" else "-"
    }
    private fun normalizeIt():Pair<BigInteger,BigInteger> {
        var a = "1".toBigInteger()
        var b = "1".toBigInteger()
        for (looper in 1..this.rational.first.toInt() ){
            if (this.rational.first%looper.toBigInteger() == "0".toBigInteger() && this.rational.second%looper.toBigInteger() == "0".toBigInteger()) {
                a = this.rational.first / looper.toBigInteger()
                b = this.rational.second / looper.toBigInteger()
            }
        }
        return Pair<BigInteger,BigInteger>(a,b)
    }

    override fun toString(): String {
        return "${this.minussign}" +
                "${this.normalized.first}" +
                "${if (this.normalized.second != 1.toBigInteger()) "/".plus(this.normalized.second) else ""}"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Rational) throw NoSuchElementException("What are you going compaire? ")
        return if ((this.normalized.first == other.normalized.first) && (this.normalized.second == other.normalized.second)
            && (this.minussign == other.minussign)) true else false
    }

}
infix  fun Int.divBy(b:Int):Rational = Rational(this.toBigInteger(),b.toBigInteger())
infix  fun Long.divBy(b:Long):Rational =Rational(this.toBigInteger(),b.toBigInteger())
infix  fun BigInteger.divBy(b:BigInteger):Rational =Rational(this,b)

fun String.toRational():Rational {
    var pair = this.split("/")
    var outp: Rational
    when {
        pair.size == 0 -> outp = Rational(0.toBigInteger(),0.toBigInteger())
        pair.size ==1 -> outp = Rational(pair[0].toBigInteger(),1.toBigInteger())
        else -> outp = Rational(pair[0].toBigInteger(),pair[1].toBigInteger())
    }
    return outp
}

operator  fun Rational.unaryMinus(): Rational {
    if (this.minussign == "") this.minussign= "-" else this.minussign = ""
    return this
}
infix operator fun Rational.compareTo(b: Rational) :Int {
    return (this.rational.first/this.rational.second ).compareTo(b.rational.first/b.rational.second)
}
infix operator fun Rational.plus(b:Rational): Rational = Rational(this.rational.first*b.rational.second +
        this.rational.second*b.rational.first,
    this.rational.second*b.rational.second)
infix operator fun Rational.minus(b:Rational): Rational = Rational(this.rational.first*b.rational.second -
        this.rational.second*b.rational.first,
    this.rational.second*b.rational.second)
infix operator fun Rational.times(b:Rational): Rational = Rational(this.rational.first*b.rational.first ,
    this.rational.second*b.rational.second)
infix operator fun Rational.div(b:Rational): Rational = Rational(this.rational.first*b.rational.second ,
    this.rational.second*b.rational.first)

operator fun Rational.rangeTo(b:Rational): ClosedRange<Float> = this.rational.first.toFloat()/this.rational.second.toFloat()..b.rational.first.toFloat()/b.rational.second.toFloat()
operator fun ClosedRange<Float>.contains(b: Rational): Boolean = if ((b.rational.first.toFloat()/b.rational.second.toFloat()>=this.start)
    && (b.rational.first.toFloat()/b.rational.second.toFloat()<=this.endInclusive) ) true else false

fun main() {

    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    5 divBy 6 == sum

    val difference: Rational = half - third
    1 divBy 6 == difference

    val product: Rational = half * third
    1 divBy 6 == product

    val quotient: Rational = half / third
    3 divBy 2 == quotient

    val negation: Rational = -half
    -1 divBy 2 == negation

    (2 divBy 1).toString() == "2"
    (-2 divBy 4).toString() == "-1/2"
    "117/1098".toRational().toString() == "13/122"

    val twoThirds = 2 divBy 3
    half < twoThirds

    half in third..twoThirds

    2000000000L divBy 4000000000L == 1 divBy 2

    "912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
}

