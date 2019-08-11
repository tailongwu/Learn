using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesignPattern.Strategy
{
    class Strategy_Rebate: Strategy
    {
        private double _discount;

        public Strategy_Rebate(double discount)
        {
            Discount = discount;
        }

        public double Discount
        {
            get
            {
                return _discount;
            }
            set
            {
                _discount = value;
            }
        }

        public override double Algorithm(double money)
        {
            return money * Discount;
        }
    }
}
