case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

// 4.1
trait Option[+A] {
    def map[B](f: A => B): Option[B] = this match {
        case None => None
        case Some(x) => Some(f(x))
    }

    def flatMap[B](f: A => Option[B]): Option[B] = this map(f) getOrElse None

    def getOrElse[B >: A](default: => B): B = this match {
        case None => default
        case Some(x) => x
    }
    
    def orElse[B >: A](ob: => Option[B]): Option[B] = this map(Some(_)) getOrElse(ob)

    def filter(f: A => Boolean): Option[A] = flatMap(x => if (f(x)) Some(x) else None)
}

// 4.2
def mean(xs: Seq[Double]): Option[Double] = if (xs.isEmpty) None else Some(xs.sum / xs.length)
def variance(xs: Seq[Double]): Option[Double] = mean(xs) flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))
println(variance(Seq(1, 2, 3)))
println(variance(Seq()))

// 4.3
def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = a.flatMap(x => b.map(y => f(x, y)))
println(map2(Some(2), Some(3))((x, y) => x * y))

// 4.4
def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => Some(Nil)
    case h :: t => h.flatMap(x => sequence(t) map (x :: _))
}
def sequence2[A](a: List[Option[A]]): Option[List[A]] = a.foldRight[Option[List[A]]](Some(Nil))((x, y) => map2(x, y)(_ :: _))
println(sequence(List(Some(1), Some(2))))

// 4.5
def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil => Some(Nil)
    // case h :: t => f(h).flatMap(x => traverse(t)(f) map (x :: _))
    case h :: t => map2(f(h), traverse(t)(f))(_ :: _)
}
def traverse2[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a.foldRight[Option[List[B]]](Some(Nil))((x, y) => map2(f(x), y)(_ :: _))
println(traverse(List(1, 2))(x => Some(x)))

// 4.6
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

trait Either[+E, +A] {
    def map[B](f: A => B): Either[E, B] = this match {
        case Left(x) => Left(x)
        case Right(x) => Right(f(x))
    }

    def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
        case Left(x) => Left(x)
        case Right(x) => f(x)
    }

    def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
        case Left(_) => b
        case Right(x) => Right(x)
    }

    def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = this flatMap(x => b.map(y => f(x, y)))
}

// 4.7
def sequenceEither[E, A](xs: List[Either[E, A]]): Either[E, List[A]] = xs match {
    case Nil => Right(Nil)
    case h :: t => h.flatMap(x => sequenceEither(t).map(y => x :: y))
}
def sequenceEither2[E, A](xs: List[Either[E, A]]): Either[E, List[A]] = xs.foldRight[Either[E, List[A]]](Right(Nil))((x, y) => x.map2(y)(_ :: _))
def sequenceEither3[E, A](xs: List[Either[E, A]]): Either[E, List[A]] = traverseEither(xs)(x => x)

def traverseEither[E, A, B](xs: List[A])(f: A => Either[E, B]): Either[E, List[B]] = xs match {
    case Nil => Right(Nil)
    case h :: t => f(h).flatMap(x => traverseEither(t)(f).map(y => (x :: y)))
}
def traverseEither2[E, A, B](xs: List[A])(f: A => Either[E, B]): Either[E, List[B]] = xs.foldRight[Either[E, List[B]]](Right(Nil))((x, y) => f(x).map2(y)(_ :: _))

// 4.8
// Either左边为List