using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesignPattern.Strategy
{
    class Strategy_Normal: Strategy
    {
        public override double Algorithm(double money)
        {
            return money;
        }
    }
}
