﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Sample2.Controllers
{
    public class Test2Controller : ApiController
    {
        public IEnumerable<string> Get()
        {
            return new string[] { "valueTest2_1", "valueTest2_2" };
        }
    }
}
