package rationals
import java.math.BigDecimal
import java.math.BigInteger
import java.util.NoSuchElementException

class Rational (numerator:Int,denominator:Int){
    val rational:Pair<Int,Int> = Pair(numerator,denominator)
    val normalized: Pair<Int,Int> = normalizeIt()
    var minussign: String
    init {
        this.minussign = if((rational.first > 0 && rational.second > 0)
            or (rational.first < 0 && rational.second < 0)) "" else "-"
    }
    private fun normalizeIt():Pair<Int,Int> {
        var a = 1
        var b = 1
        for (looper in 1..Math.abs(this.rational.first.toInt())){
            if (this.rational.first.toInt()%looper == 0 && this.rational.second.toInt()%looper == 0) {
                a = this.rational.first.toInt() / looper
                b = this.rational.second.toInt() / looper
            }
        }
        return Pair<Int,Int>(Math.abs(a),Math.abs(b))
    }

    override fun toString(): String {

        return "${this.minussign}" +
                "${Math.abs(this.rational.first)}" +
                "${if (Math.abs(this.rational.second) != 1) "/".plus(Math.abs(this.rational.second)) else ""}"
//                "(Normalized version: ${minussign}${Math.abs(normalized.first)}" +
//                "${if(Math.abs(normalized.second) != 1) "/".plus(Math.abs(normalized.second))
//                else ""

    }

    override fun equals(other: Any?): Boolean {
        if (other !is Rational) throw NoSuchElementException("What are you going compaire? ")
        return if ((this.normalized.first == other.normalized.first) && (this.normalized.second == other.normalized.second)
            && (this.minussign == other.minussign)) true else false
    }

}
infix  fun Int.divBy(b:Int):Rational = Rational(this,b)
infix  fun Long.divBy(b:Long):Rational =Rational(this.toInt(),b.toInt())
infix  fun BigInteger.divBy(b:BigInteger):Rational =Rational(this.toInt(),b.toInt())

fun String.toRational():Rational {
    var pair = this.split("/")
    var outp: Rational
    when {
        pair.size == 0 -> outp = Rational(0,0)
        pair.size ==1 -> outp = Rational(pair[0].toInt(),1)
        else -> outp = Rational(pair[0].toInt(),pair[1].toInt())
    }
    return outp
}

operator  fun Rational.unaryMinus(): Rational {
    if (this.minussign == "") this.minussign= "-" else this.minussign = ""
    return this
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



fun main() {

    val a:Rational = Rational(117,1098)
    println(a.toString() )
    val b:Rational = Rational(2,1)
    println(b.toString() )
    val c:Rational = Rational(-2,4)
    println(c.toString() )
    val d:Rational = Rational(-21,105)
    println(d.toString() )

   val half = 1 divBy 2
   val third = 1 divBy 3

    println("half = $half third = $third")
    val sum: Rational = half +  third
    println("Sum $half plus $third = $sum ")
    println(5 divBy 6)
    println(" String notaion -3/33 : ${"-3/33".toRational()}")
    println(" String notaion 23/1 : ${"23/1".toRational()}")
    println(" String notaion 123 : ${"123".toRational()}")
    println("Compare to rationals :")
    println(5 divBy 6 == sum)
    println(2 divBy 6 == "3/9".toRational())
    println("2 divBy 6 = ${2 divBy 6}")
    println("\"3/9\".toRational() = ${"3/9".toRational()}")
    val threenines = "3/9".toRational()
    println("third is equal nines :  ${third==threenines} ")
    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    println("before negation operation: $half")
    val negation: Rational = -half
    println(" sign is:  ${half.minussign}")
    println("negation operation: $half")
    println(-1 divBy 2 == negation)

    println("(2 divBy 1).toString() == 2 :  ${(2 divBy 1).toString() == "2"}")
    println(" 2 divBY 1 : ${2 divBy 1}")
    println(" 2 divBY 1 : ${(2 divBy 1).toString()}")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
//    println(half < twoThirds)

//    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)
    println(2L divBy 4L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}
