package methods;

import java.net.URL;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.stream.JsonToken;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jwt.proc.JWTClaimsSetVerifier;

import fr.m2i.models.User;
import fr.m2i.singleton.PrivateKey;

import com.nimbusds.jose.crypto.impl.ECDSA;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenMethods {
	
	//private PrivateKey key;
	
	
	public static String issueToken(User user) {  
		
        try {
        	
        	System.out.println("TOKEN IS GENERATED");
        	System.out.println(user.getSuperAdmin());
            ECKey key = new ECKeyGenerator(Curve.P_256)
               .keyID("1")
               .generate();
            
            PrivateKey.getInstance().setKey(key);
            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.ES256)
                       .type(JOSEObjectType.JWT)
                       .keyID(key.getKeyID())
                       .build();
            System.out.println("KEY");
            System.out.println(key);
            
            JWTClaimsSet payload = new JWTClaimsSet.Builder()
                       //.issuer(uriInfo.getAbsolutePath().toString())
                       .issueTime(new Date())

                       .subject("logged user")
                       .expirationTime(Date.from(Instant.now().plusSeconds(600)))
                       //.issuer("localhost:4200")
                       .claim("id", user.getId())
                       .claim("email", user.getEmail())
                       .claim("firstname", user.getFirstname())
                       .claim("lastname", user.getLastname())
                       .claim("accessRight", user.getAccessRight())
                       .claim("superAdmin", user.getSuperAdmin())
                       .build();
            
            
            SignedJWT signedJWT = new SignedJWT(header, payload);
            signedJWT.sign(new ECDSASigner(key.toECPrivateKey()));
            String jwtToken = signedJWT.serialize();
            
            return jwtToken;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
            
        }
            
        public static boolean validateToken(String token) throws JOSEException, ParseException { 
            //Validation
            /**ECKey key3 = new ECKeyGenerator(Curve.P_256)
                    .keyID("1")
                    .generate();**/
            ECKey newKey = PrivateKey.getInstance().getKey();

            System.out.println(newKey);
            


            try {
            	
	            Boolean isValid = SignedJWT.parse(token)
	           	    .verify(new ECDSAVerifier(newKey.toECPublicKey()));
	            if (isValid) {
	            	
	            	SignedJWT decodedJWT = SignedJWT.parse(token);
	                //String header1 = decodedJWT.getHeader().toString();
	                //System.out.println(header1);
	                Payload payload1 = decodedJWT.getPayload();
	                //Object id = decodedJWT.getClaim("id");
	                System.out.println(payload1.toJSONObject());
	                return true;
	            }

            }
            catch(Exception e) {
            	System.out.println("No Private key available");
            	return false;
            }

            return false;
          
    }

        
        public static Map<String, Object> getClaimsToken (String token) throws JOSEException, ParseException{
        	ECKey newKey = PrivateKey.getInstance().getKey();
        	
        	//JWTConsumer jwtConsumer = new JwtConsumerBuilder()
        	
        	 /*let decodedJWT = SignedJWT.parse(Token);
             var header = decodedJWT.getHeader().toString();
             var payload = decodedJWT.getPayload().toString();*/
        	
        	//Map<String, Object> claims = this.claimSetConverter.convert(JWTClaimsSet.parse(token).getClaims());
        	
        	JWTClaimsSet decodedJWT = JWTClaimsSet.parse(token);
        	Map<String, Object> claims = decodedJWT.getClaims();
        	
        	/*String payload1 = decodedJWT.getPayload() .toString(); 
        	decodedJWT.
        	
        	Map<String, Object> claims= JWTClaimsSet.parse(token).getClaims();
        	System.out.println(JWTClaimsSet.parse(token).getClaims());*/
        	
        	return claims;
        	
        	//Claims tokenClaims=  	JWTClaimsSet.parse(token).getClaims();
        	
        	//tokenClaims= this.claimeSetCo JWTClaimsSet.parse(token).getClaims()
        	
        	//SignedJWT decodedJWT = SignedJWT.parse(token);
        	//decodedJWT.getPayload().
          
                     //Decode
           /* if (isValid) {
            	SignedJWT decodedJWT = SignedJWT.parse(token);
                String header1 = decodedJWT.getHeader().toString();
                System.out.println(header1);
                String payload1 = decodedJWT.getPayload().toString();  
                System.out.println(payload1);
                
                //test cliams
                JWTClaimsSet decodJWT = JWTClaimsSet.parse(token);
            	Map<String, Object> claims =decodJWT.getClaims();*/
        	
        }


      //JWTClaimsSet decodedJWT = JWTClaimsSet.parse(token);
	

}
