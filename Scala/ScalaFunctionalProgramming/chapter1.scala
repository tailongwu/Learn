// Demo
// 考虑买咖啡例子

// Step1：cc.charge含有副作用，导致很难测试
class Cafe {
    def buyCoffee(cc: CreditCard): Coffee = {
        val cup = new Coffee()
        cc.charge(cup.price)
        cup
    }
}

// Step2：charge可以使用接口，使用mock测试。但是buyCoffee很难被复用，比如买了多杯咖啡
class Cafe {
    def buyCoffee(cc: CreditCard, p: Payments): Coffee = {
        val cup = new Coffee()
        p.charge(cc, cup.price)
        cup
    }
}

// Step3：将创建和处理过程分开，消除副作用，提升复用性。也是引用透明的，前两者并不是
class Cafe {
    def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
        val cup = new Coffee()
        (cup, Charge(cc, cup.price))
    }
    def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
        val purchase: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
        val (coffees, charges) = purchase.unzip(coffees, charges.reduce((c1, c2) => c1.combine(c2)))
    }
}
case class Charge(cc: CreditCard, amount: Double) {
    def combine(other: Charge): Charge = {
        if (other.cc == cc) {
            Charge(cc, amount + other.amount)
        } else {
            throw new Exception("Can't combine charges to different card.")
        }
    }
}