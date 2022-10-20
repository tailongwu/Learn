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

// 3.1
val x = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + List.sum(t)
    case _ => 101
}
println(x)

// 3.2
def dropHead[A]: List[A] => List[A] = {
    case Nil => Nil
    case Cons(_, tail) => tail
}
println(dropHead(Cons(1, Cons(2, Cons(3, Nil)))))

// 3.3
def setHead[A](x: A, l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, tail) => Cons(x, tail)
}
println(setHead(2, Cons(1, Cons(2, Cons(3, Nil)))))

// 3.4
def drop[A](l: List[A], n: Int): List[A] = {
    if (n == 0) l
    else {
        l match {
            case Nil => Nil
            case Cons(h, tail) => drop(tail, n - 1) 
        }
    }
}
println(drop(Cons(1, Cons(2, Cons(3, Cons(4, Nil)))), 3))

// 3.5
def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, tail) if(f(h)) => dropWhile(tail, f)
    case _ => l
}
def dropWhile2[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h, tail) if f(h) => dropWhile2(tail)(f)
    case _ => l
}
println(dropWhile(Cons(2, Cons(2, Cons(3, Cons(4, Nil)))), (x: Int) => x % 2 == 0))
println(dropWhile2(Cons(2, Cons(2, Cons(3, Cons(4, Nil)))))(x => x % 2 == 0))

// 3.6
def init[A](l: List[A]): List[A] = l match {
    case Nil => l
    case Cons(h, Nil) => Nil
    case Cons(h, t) => Cons(h, init(t))
}
println(init(Cons(1, Cons(2, Cons(3, Cons(4, Nil))))))
println(init(Cons(1, Nil)))
println(init(Nil))

// 3.7
// 不可以，foldRight会一直遍历列表到末尾，需要非严格计算支持提前终止

// 3.8
println(foldRight(List(1, 2, 3, 4), Nil:List[Int])(Cons(_, _)))

// 3.9
def length[A]: List[A] => Int = foldRight(_, 0)((_, x) => x + 1)
println(length(List(1, 2, 3, 4, 5, 6)))

// 3.10
def foldLeft[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    as match {
        case Nil => z
        case Cons(h, t) => foldLeft(t, f(h, z))(f)
    }
}

// 3.11
def sum: List[Int] => Int = foldLeft(_, 0)((x, y) => x + y)
def product: List[Int] => Int = foldLeft(_, 1)((x, y) => x * y)
def length2[A]: List[A] => Int = foldLeft(_, 0)((_, x) => x + 1)
println(sum(List(1, 2, 3, 4)))
println(product(List(1, 2, 3, 4)))
println(length2(List(1, 2, 3, 4)))

// 3.12
def reverse[A]: List[A] => List[A] = foldLeft[A, List[A]](_, Nil)((x, l) => Cons(x, l))
println(reverse(List(1, 2, 3, 4)))

// 3.13 需要新解决办法，reverse比较tricky
def foldRightViaFoldLeft[A, B](as: List[A], z: B)(f: (A, B) => B): B = foldLeft(reverse(as), z)(f)
def foldLeftViaFoldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = foldRight(reverse(as), z)(f)

// 3.14
def append[A](xs: List[A], ys: List[A]): List[A] = foldRight[A, List[A]](xs, ys)((x, l) => Cons(x, l))
println(append(List(1, 2), List(3, 4)))

// 3.15
def concat[A](l: List[List[A]]): List[A] = foldRight[List[A], List[A]](l, Nil)(append)

// 3.16
def addOne(xs: List[Int]): List[Int] = foldRight[Int, List[Int]](xs, Nil)((x, l) => Cons(x + 1, l))
println(addOne(List(1, 2, 3)))

// 3.17
def doubleListToString: List[Double] => List[String] = foldRight[Double, List[String]](_, Nil)((x, l) => Cons(x.toString, l))
println(addOne(List(1, 2, 3)))

// 3.18
def map[A, B](xs: List[A])(f: A => B): List[B] = foldRight[A, List[B]](xs, Nil)((x, l) => Cons(f(x), l))
println(map(List(1, 2, 3, 4))(_ * 2))

// 3.19
def filter[A](xs: List[A])(f: A => Boolean): List[A] = foldRight[A, List[A]](xs, Nil)((x, l) => if (f(x)) Cons(x, l) else l)
println(filter(List(1, 2, 3, 4))(x => x % 2 != 1))

// 3.20
def flattern[A](xss: List[List[A]]): List[A] = foldRight[List[A], List[A]](xss, Nil)(append)
def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = flattern(map[A, List[B]](as)(f))
def flatMap2[A, B](as: List[A])(f: A => List[B]): List[B] = concat(map(as)(f))
println(flatMap(List(1, 2, 3))(i => List(i, i)))

// 3.21
def filter2[A](xs: List[A])(f: A => Boolean): List[A] = flatMap(xs)(x => if (f(x)) Cons(x, Nil) else Nil)
println(filter2(List(1, 2, 3, 4))(x => x % 2 != 1))

// 3.22
def add(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, add(t1, t2))
}
println(add(List(1, 2, 3, 4), List(5, 6, 7)))

// 3.23
def zipWith[A, B, C](xs: List[A], ys: List[B])(f: (A, B) => C): List[C] = (xs, ys) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
}
println(zipWith(List(1, 2, 3, 4), List(5, 6, 7))(_ + _))

// 3.24
@annotation.tailrec
def contain[A](xs: List[A], ys: List[A]): Boolean = (xs, ys) match {
    case (_, Nil) => true
    case (Nil, _) => false
    case (Cons(h1, t1), Cons(h2, t2)) => {
        if (h1 == h2) contain(t1, t2)
        else false
    }
}
@annotation.tailrec
def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = (sup, sub) match {
    case (_, Nil) => true
    case (Nil, _) => false
    case (Cons(h1, t1), Cons(h2, t2)) => {
        if (h1 == h2) contain(t1, t2)
        else hasSubsequence(t1, sub)
    }
}
println(hasSubsequence(List(1, 2, 3, 4), List(2, 3)))



sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

// 3.25
def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => size(l) + size(r) + 1
}
println(size(Branch(Branch(Leaf(1), Leaf(2)), Branch(Branch(Leaf(3), Leaf(4)), Leaf(5)))))
println(size2(Branch(Branch(Leaf(1), Leaf(2)), Branch(Branch(Leaf(3), Leaf(4)), Leaf(5)))))

// 3.26
def maximum(t: Tree[Int]): Int = t match {
    case Leaf(x) => x
    case Branch(l, r) => maximum(l) max maximum(r)
}
println(maximum(Branch(Branch(Leaf(1), Leaf(2)), Branch(Branch(Leaf(7), Leaf(4)), Leaf(5)))))
println(maximum2(Branch(Branch(Leaf(1), Leaf(2)), Branch(Branch(Leaf(7), Leaf(4)), Leaf(5)))))

// 3.27
def depth(t: Tree[Int]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => (depth(l) max depth(r)) + 1
}
println(depth(Branch(Branch(Leaf(1), Leaf(2)), Branch(Branch(Leaf(3), Leaf(4)), Leaf(5)))))
println(depth2(Branch(Branch(Leaf(1), Leaf(2)), Branch(Branch(Leaf(3), Leaf(4)), Leaf(5)))))

// 3.28
def mapTree[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(x) => Leaf(f(x))
    case Branch(l, r) => Branch(mapTree(l)(f), mapTree(r)(f))
}
println(mapTree(Branch(Branch(Leaf(1), Leaf(2)), Branch(Branch(Leaf(3), Leaf(4)), Leaf(5))))(_ * 2))
println(mapTree2(Branch(Branch(Leaf(1), Leaf(2)), Branch(Branch(Leaf(3), Leaf(4)), Leaf(5))))(_ * 2))

// 3.29
def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(x) => f(x)
    case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
}
def size2[A](t: Tree[A]): Int = fold(t)(_ => 1)(_ + _ + 1)
// 为啥不能_ => _？？？
def maximum2(t: Tree[Int]): Int = fold(t)(x => x)(_ max _)
def depth2(t: Tree[Int]): Int = fold(t)(_ => 0)(_ max _ + 1)
def mapTree2[A, B](t: Tree[A])(f: A => B): Tree[B] = fold[A, Tree[B]](t)(x => Leaf(f(x)))(Branch(_, _))