using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;

namespace Sample2
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Web API 配置和服务

            // Web API 路由
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );

            config.Routes.MapHttpRoute(
                name: "DefaultApi2",
                routeTemplate: "api/{controller}/{action}",
                defaults: new { action = "Index" }
            );
        }
    }
}
