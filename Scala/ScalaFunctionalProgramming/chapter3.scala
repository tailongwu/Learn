sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]
object List {
    def sum: List[Int] => Int = {
        case Nil => 0
        case Cons(h, t) => h + sum(t)
    }

    def product: List[Int] => Int = {
        case Nil => 1
        case Cons(h, t) => h * product(t)
    }

    def apply[A](as: A*): List[A] = {
        if (as.isEmpty) Nil
        else Cons(as.head, apply(as.tail: _*))
    }
}

def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    as match {
        case Nil => z
        case Cons(h, t) => f(h, foldRight(t, z)(f))
    }
}

def sum: List[Int] => Int = foldRight(_, 0)((x, y) => x + y)
def product: List[Int] => Int = foldRight(_, 1)(_ * _)
println(sum(Cons(1, Cons(2, Nil))))
println(product(Cons(1, Cons(2, Nil))))