using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;

namespace CSharp
{
    public static class MultipleThreadTest
    {
        // 线程通信，两个线程打印12A34B...5152Z
        public static void Print1()
        {
            ManualResetEvent waitHandler = new ManualResetEvent(false);
            new Thread(() => {
                for (int i = 1; i < 53; i += 2)
                {
                    waitHandler.WaitOne();
                    Console.Write(i);
                    Console.Write(i + 1);
                    waitHandler.Reset();
                }
            }).Start();
            new Thread(() => {
                for (int i = 0; i < 26; i++)
                {
                    Thread.Sleep(1000);
                    Console.Write((char)(i + 'A'));
                    waitHandler.Set();
                }
            }).Start();
            Console.WriteLine("End");
        }

        private static object lockObject = new object();
        private static int count = 1;
        public static void Print2()
        {
            new Thread(()=> {
                for (int i = 1; i < 53; i += 2)
                {
                    if ((count & 1) == 1)
                    {
                        Console.Write(i);
                        Console.Write(i + 1);
                        AddCount();
                    }
                    else
                    {
                        i -= 2;
                        Thread.Sleep(500);
                    }
                }
            }).Start();
            new Thread(() => {
                for (int i = 0; i < 26; i++)
                {
                    if((count & 1) == 0)
                    {
                        Console.Write((char)(i + 'A'));
                        AddCount();
                    }
                    else
                    {
                        i--;
                        Thread.Sleep(500);
                    }
                }
            }).Start();
        }
        private static void AddCount()
        {
            lock (lockObject)
            {
                count++;
            }
        }

        public static void JoinTest()
        {
            Thread th1 = new Thread(() =>
            {
                Console.WriteLine("th1");
            });
            Thread th2 = new Thread(() =>
            {
                th1.Join();
                Console.WriteLine("th2");
            });
            Thread th3 = new Thread(() =>
            {
                th1.Join();
                Console.WriteLine("th3");
            });
            th3.Start();
            th2.Start();
            th1.Start();
        }
    }
}
