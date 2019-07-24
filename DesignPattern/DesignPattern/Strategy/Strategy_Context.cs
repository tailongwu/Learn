using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesignPattern.Strategy
{
    class Strategy_Context
    {
        Strategy strategy;

        public Strategy_Context(Constants.Strategy type)
        {
            switch(type)
            {
                case Constants.Strategy.Normal:
                    strategy = new Strategy_Normal();
                    break;
                case Constants.Strategy.Rebate:
                    strategy = new Strategy_Rebate(0.8);
                    break;
                case Constants.Strategy.Return:
                    strategy = new Strategy_Return(300, 100);
                    break;
                default:
                    break;
            }
        }

        public double GetResult(double money)
        {
            return strategy.Algorithm(money);
        }
    }
}
