object MyModule {
    def abs(n: Int): Int = {
        if (n < 0) -n
        else n
    }

    def factorial(n: Int): Int = {
        def go(n: Int, acc: Int): Int = {
            if (n <= 0) acc
            else go(n - 1, acc * n)
        }
        go(n, 1)
    }

    private def formatAbs(n: Int) = {
        val msg = "The absolute value of %d is %d"
        msg.format(n, abs(n))
    }

    private def formatFactorial(n: Int) = {
        val msg = "The factorial of %d is %d"
        msg.format(n, factorial(n))
    }

    // 泛化为一个通用函数，分离变与不变
    private def formatResult(name: String, n: Int, f: Int => Int) = {
        val msg = "The %s of %d is %d"
        msg.format(name, n, f(n))
    }

    def main(args: Array[String]): Unit = {
        println(formatResult("absolute value", -42, abs))
        println(formatResult("factorial", 7, factorial))
    }
}

// Step1：从数组中找到第一个key的位置，否则返回-1
def findFirst(ss: Array[String], key: String): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = {
        if (n == ss.length) -1
        else if (ss(n) == key) n
        else loop(n + 1)
    }
    loop(0)
}

// Step2：多态函数，并使用f代替判等的硬编码
def findFirst[A](ss: Array[A], f: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: A): Int = {
        if (n == ss.length) -1
        else if (f(ss(n))) n
        else loop(n + 1)
    }
    loop(0)
}