using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DesignPattern.Simple_Factory;
using DesignPattern.Strategy;

namespace DesignPattern
{
    class Program
    {
        static void Main(string[] args)
        {
            // 简单工厂模式
            // 优点：1. 创建和使用分离；2. 不在乎实现，只需要知道类名和参数；
            // 缺点：1. factory职责过重；2. 需要添加新的实现；
            Operation oper = OperationFactory.CreateOperation("+");
            oper.NumberA = 1;
            oper.NumberB = 4;
            Console.WriteLine(oper.GetResult());


            // 工厂模式
            // 对比：在简单工厂模式中，要生成哪种模式，我们需要传type，然后根据type返回相应的模式。而在工厂模式中，客户端需要哪种模式，直接new哪种模式。
            // 优点：如果要扩展简单工厂模式，那么将违背开闭原则；工厂模式不会。


            // 策略模式
            // 优点：1. 算法切换方便；2. 扩展性好
            // 缺点：1. 策略类需要暴露；
            // 评注：简单工厂模式需要暴露两个类，策略模式只需要暴露一个类
            Strategy_Context strategy_Context = new Strategy_Context(Constants.Strategy.Rebate);
            Console.WriteLine(strategy_Context.GetResult(300));


            // 单一职责原则
            // 优点：1. 降低类复杂度；2. 提高类的可读性、维护性；3. 降低风险；
            // 难点：1. 解耦


            // 开闭原则
            // 解释：对扩展开放，对修改关闭
            // 原则：抽象。抽象基类（尽量不要修改）；派生类继承基类实现扩展；参数、引用尽量使用接口或者抽象类而不是实现类；
            // 优点：可复用、可维护


            // 依赖倒转原则
            // 解释：高层模块不应该依赖底层模块。两个都应该依赖抽象。抽象不应该依赖细节，细节应该依赖抽象
            // 示例：高层模块：逻辑层；低层模块：数据库层；高层依赖低层，那么想切换其它数据库访问方式，高层不可复用。


            // 里氏替换原则
            // 解释：父类出现的地方都可以用子类替换。
            // 评注：所以开闭原则、依赖倒转原则才有可能。只依赖接口。


            // 装饰模式
            // 解释：动态给一个对象添加一些额外的职责。


            // 代理模式
            // 解释：为其他对象提供一种代理以控制要访问的对象

            // 原型模式
            // 解释：通过clone创建新对象

            // 模板模式
            // 解释：有多个操作，它们有的步骤是相同的，有的是不同的，将相同的抽象出来，其他的放在子类实现

            // 迪米特法则
            // 解释：如果两个类之间不能直接通信，那么通过第三方通信

            // 外观模式
            // 解释：为子系统提供一致的接口等，让子系统更加容易使用接口（直接买多只股票，找代理人买多只股票）

            // 建造者模式：
            // 解释：一个复杂的创建工作，通常由多个子对象组成，而各个子对象变化大。所以将变和不便分离开。与工厂模式类似，但是建造者模式更加关注零件
        }
    }
}
