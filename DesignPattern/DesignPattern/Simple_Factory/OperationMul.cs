﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesignPattern.Simple_Factory
{
    class OperationMul: Operation
    {
        public override double GetResult()
        {
            return NumberA * NumberB;
        }
    }
}
