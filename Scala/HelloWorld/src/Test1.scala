import HelloWorld.abs

object Test1 {
  def fib(n: Int): Int = {
    def go(n: Int, val1: Int, val2: Int): Int = {
      if (n == 0) val1
      else go(n - 1, val2, val1 + val2)
    }

    go(n, 0, 1)
  }

  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    def rec(n: Int): Boolean = {
      if (n + 1 == as.length) true
      else if (!ordered(as(n), as(n + 1))) false
      else rec(n + 1)
    }

    rec(0)
  }

  def main(args: Array[String]): Unit = {
    new Function9
    val func = new Function2[Int, Int, Boolean]{
      def apply(a: Int, b: Int) = a < b
    }
    println(isSorted(Array(11, 10, 9), func))
  }
}
