using Microsoft.AspNetCore.Mvc;
using NetCoreAuth.Areas.Identity.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;

namespace NetCoreAuth.Controllers
{
    public class TokenController: Controller
    {
        public string GetToken(string userName, string password)
        {
            bool success = userName.Equals("wtl") && password.Equals("123");
            if (!success)
            {
                return "No authentication!";
            }

            JWTTokenOptions jwtTokenOptions = new JWTTokenOptions();

            var claims = new Claim[]
            {
                new Claim(ClaimTypes.Sid, userName),
                new Claim(ClaimTypes.Name, userName),
                new Claim(ClaimTypes.Role, "user")
            };

            var token = new JwtSecurityToken(
                issuer: jwtTokenOptions.Issuer,
                audience: jwtTokenOptions.Audience,
                claims: claims,
                notBefore: DateTime.Now,
                expires: DateTime.Now.AddMinutes(1),
                signingCredentials: jwtTokenOptions.Credentials
            );

            string jwtToken = new JwtSecurityTokenHandler().WriteToken(token);
            return jwtToken;
        }
    }
}
