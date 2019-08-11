using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Text;

namespace CSharp
{
    public static class StringTest
    {
        public static void test1()
        {
            string s1 = "abc";
            string s2 = "abc";
            Console.WriteLine("test1:");
            Console.WriteLine(GetMemory(s1));
            Console.WriteLine(GetMemory(s2));
            Console.WriteLine(s1 == s2); // true
            Console.WriteLine(s1.Equals(s2)); // true
        }

        public static void test2()
        {
            string s1 = new string("abc");
            string s2 = "abc";
            Console.WriteLine("test2:");
            Console.WriteLine(GetMemory(s1));
            Console.WriteLine(GetMemory(s2));
            Console.WriteLine(s1 == s2); // true，内部是equals
            Console.WriteLine(s1.Equals(s2)); // true

        }

        // 获取引用对象地址
        public static string GetMemory(object o)
        {
            GCHandle h = GCHandle.Alloc(o, GCHandleType.Pinned);
            IntPtr addr = h.AddrOfPinnedObject();
            return addr.ToString("X");
        }
    }
}
