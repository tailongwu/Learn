// 2.1
def fib(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, a: Int, b: Int): Int = {
        if (n == 1) a
        else if (n == 2) b
        else go(n - 1, b, a + b)
    }
    go(n, 0, 1)
}
println(fib(6))


// 2.2
def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean = {
        if (n + 1 == as.length) true
        else if (!ordered(as(n), as(n + 1))) false
        else go(n + 1)
    }
    go(0)
}
println(isSorted(Array(1, 2, 5, 4), (x: Int, y: Int) => x < y))


// 2.3
def curry[A, B, C](f: (A, B) => C): A => (B => C) = A => B => f(A, B)

// 2.4
def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)

// 2.5
def compose[A, B, C](f: B => C, g: A => B): A => C = a => f(g(a))