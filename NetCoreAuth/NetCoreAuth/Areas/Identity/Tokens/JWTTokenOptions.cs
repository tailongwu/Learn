using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NetCoreAuth.Areas.Identity.Tokens
{
    public class JWTTokenOptions
    {
        public JWTTokenOptions()
        {
            CreateKey();
        }

        public string Issuer { get; set; } = "NetCoreAuth";

        public string Audience { get; set; } = "Client";

        public string SecurityKey { get; private set; } = "a secret that needs to be at least 16 characters long";

        public void SetSecurityKey(string value)
        {
            SecurityKey = value;

            CreateKey();
        }

        public SigningCredentials Credentials { get; set; }

        public SymmetricSecurityKey Key { get; set; }

        private void CreateKey()
        {
            Key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(SecurityKey));
            Credentials = new SigningCredentials(Key, SecurityAlgorithms.HmacSha256);
        }
    }
}
