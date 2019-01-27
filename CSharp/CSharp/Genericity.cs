using System;
using System.Collections.Generic;
using System.Text;

namespace CSharp
{
    class Genericity
    {
        O<int> x = new O<int>(1, 2);
        x.
    }

    public class O<T>
    {
        private T _param1;
        private T _param2;

        public O(T param1, T param2)
        {
            this._param1 = param1;
            this._param2 = param2;
        }

        public void Read()
        {
            Console.WriteLine(this._param1);
            Console.WriteLine(this._param2);
        }
    }
}
