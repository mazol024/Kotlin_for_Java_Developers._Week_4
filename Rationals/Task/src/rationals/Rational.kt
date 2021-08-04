package rationals
import java.math.BigInteger

class Rational (numerator:Int,denominator:Int){
    val rational:Pair<Int,Int> = Pair(numerator,denominator)
    //val rvalue = rational.first/rational.second
    val normalized: Pair<Int,Int> = normalizeIt()

    private fun normalizeIt():Pair<Int,Int> {

        //var normalized
        var a = 1
        var b = 1
        for (looper in 1..Math.abs(this.rational.first)){
            if (this.rational.first%looper == 0 && this.rational.second%looper == 0) {
                a = this.rational.first / looper
                b = this.rational.second / looper
            }
        }
        return Pair<Int,Int>(a,b)
    }

    override fun toString(): String {
        val sign1 = if (rational.first < 0&& rational.second >=0 ||
            rational.second < 0&& rational.first>=0) "-" else ""
        return "${sign1}" +
                "${Math.abs(rational.first)}/${Math.abs(rational.second)}  " +
                "(Normalized version: ${sign1}${Math.abs(normalized.first)}" +
                "${if(Math.abs(normalized.second) != 1) "/".plus(Math.abs(normalized.second)) 
                else ""})"
    }
}


fun main() {
    val a:Rational = Rational(117,1098)
    println(a.toString() )
    val b:Rational = Rational(122,-1)
    println(b.toString() )
    val c:Rational = Rational(42,1)
    println(c.toString() )
    val d:Rational = Rational(-21,105)
    println(d.toString() )
//    val half = 1 divBy 2
//    val third = 1 divBy 3
//
//    val sum: Rational = half + third
//    println(5 divBy 6 == sum)
//
//    val difference: Rational = half - third
//    println(1 divBy 6 == difference)
//
//    val product: Rational = half * third
//    println(1 divBy 6 == product)
//
//    val quotient: Rational = half / third
//    println(3 divBy 2 == quotient)
//
//    val negation: Rational = -half
//    println(-1 divBy 2 == negation)
//
//    println((2 divBy 1).toString() == "2")
//    println((-2 divBy 4).toString() == "-1/2")
//    println("117/1098".toRational().toString() == "13/122")
//
//    val twoThirds = 2 divBy 3
//    println(half < twoThirds)
//
//    println(half in third..twoThirds)
//
//    println(2000000000L divBy 4000000000L == 1 divBy 2)
//
//    println("912016490186296920119201192141970416029".toBigInteger() divBy
//            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}