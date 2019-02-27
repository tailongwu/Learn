using System;
using System.Collections.Generic;
using System.Text;

namespace CSharp
{
    /// <summary>
    /// 1.逻辑解耦
    /// 2. 代码复用
    /// </summary>

    public class DelegateTest
    {
        public delegate void DelegateTestWithoutPara();
        public delegate void DelegateTestWithPara(int a, int b);
        public delegate void DelegateTestSayHi();


        public void Test()
        {
            DelegateTestWithoutPara test1 = new DelegateTestWithoutPara(DoNothingWithoutPara);
            DelegateTestWithPara test2 = new DelegateTestWithPara(DoNothingWithPara);
            test1.Invoke();
            test2.Invoke(1, 2);
            
            DelegateTestSayHi sayHi1 = new DelegateTestSayHi(SayHiInChinese);
            DelegateTestSayHi sayHi2 = new DelegateTestSayHi(SayHiInEnglish);
            SayHi(sayHi1);
            SayHi(sayHi2);
        }

        public void SayHiInChinese()
        {
            Console.WriteLine("你好");
        }

        public void SayHiInEnglish()
        {
            Console.WriteLine("Hello");
        }

        public void SayHi(DelegateTestSayHi sayHi)
        {
            sayHi.Invoke();
        }

        public void DoNothingWithoutPara()
        {
            Console.WriteLine("DoNothingWithoutPara");
        }

        public void DoNothingWithPara(int a, int b)
        {
            Console.WriteLine("DoNothingWithPara: {0}.", a + b);
        }
    }
}
