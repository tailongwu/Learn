using System;
using System.Collections.Generic;
using System.Text;

namespace CSharp
{
    public class Father
    {
        public Father()
        {
            Console.WriteLine("Father constructor");
        }

        protected void FatherTest()
        {
            Console.WriteLine("FatherTest");
        }
    }

    public class Son : Father
    {
        public Son()
        {
            Console.WriteLine("Son constructor");
        }

        public void SonTest()
        {
            base.FatherTest();
            Console.WriteLine("SonTest");
        }
    }

    public interface Interface
    {
        void Test1();

    }

    public abstract class Abstract
    {
        internal abstract void Test1();
    }


    public static class ObjectOrientedTest
    {
        public static void test1()
        {

        }
    }
}
