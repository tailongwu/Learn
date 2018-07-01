在Global.asax.cs中，注册WebAPI：GlobalConfiguration.Configure(WebApiConfig.Register);
在WebAPIConfig.cs中，匹配url和class
/api/{controllerName}，如果类名叫做TestController，controllerName就是Test。在类中，方法名开头代表调用该方法的http方法。