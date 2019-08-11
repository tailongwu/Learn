using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesignPattern.Simple_Factory
{
    class Operation
    {
        private double _numberA;
        private double _numberB;
        
        public double NumberA
        {
            get
            {
                return _numberA;
            }
            set
            {
                _numberA = value;
            }
        }

        public double NumberB
        {
            get
            {
                return _numberB;
            }
            set
            {
                _numberB = value;
            }
        }

        public virtual double GetResult()
        {
            double result = 0;
            return result;
        }
    }
}
