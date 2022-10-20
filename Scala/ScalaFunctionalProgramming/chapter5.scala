def if2[A](cond: Boolean, onTrue: => A, onFalse: => A): A = if (cond) onTrue else onFalse
if2(false, println("onTrue"), println("onFalse"))

def maybeTwice(b: Boolean, i: => Int): Int = if (b) i + i else 0
println(maybeTwice(true, {println("hi"); 1}))

def maybeTwice2(b: Boolean, i: => Int): Int = {
    if (b) {
        lazy val j = i
        j + j
     } else 0
}
println(maybeTwice2(true, {println("hi"); 1}))

sealed trait Stream[+A]
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
        lazy val head = hd
        lazy val tail = tl
        Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}