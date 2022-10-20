case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

sealed trait Stream[+A] {
    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
        lazy val head = hd
        lazy val tail = tl
        Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

    // 5.1    
    def toList: List[A] = {
        @annotation.tailrec
        def go(s: Stream[A], acc: List[A]): List[A] = s match{
            case Cons(h, t) => go(t(), h() :: acc)
            case _ => acc
        }
        go(this, List())
    }

    // 5.2
    def take(n: Int): Stream[A] = this match {
        case Cons(h, t) if (n > 1) => Cons(h, t.take(n - 1))
        case Cons(h, _) if (n == 1) => Cons(h, empty)
        case _ => empty
    }

    // def drop[A](n: Int): Stream[A] = this match {
    //     case Cons(_, t) if (n > 0) => t.drop(n - 1)
    //     case _ => this
    // }

    // // 5.3
    // def takeWhile[A](p: A => Boolean): Stream[A] = this match {
    //     case Cons(h, t) if (p(h)) => Cons(h, t.takeWhile(p))
    //     case Cons(_, t) => t.takeWhile(p)
    //     case _ => empty
    // }
}

// println(Stream(1, 2, 3, 4).take(2).toList)