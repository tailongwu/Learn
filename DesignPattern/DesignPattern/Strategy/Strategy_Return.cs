using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesignPattern.Strategy
{
    class Strategy_Return: Strategy
    {
        private double _conditionMoney;
        private double _returnMoney;

        public Strategy_Return(double conditionMoney, double returnMoney)
        {

        }

        public double ConditionMoney
        {
            get
            {
                return _conditionMoney;
            }
            set
            {
                _conditionMoney = value;
            }
        }

        public double ReturnMoney
        {
            get
            {
                return _returnMoney;
            }
            set
            {
                _returnMoney = value;
            }
        }

        public override double Algorithm(double money)
        {
            double result = money;
            if (money >= ConditionMoney)
            {
                result = money - ReturnMoney;
            }
            return result;
        }
    }
}
