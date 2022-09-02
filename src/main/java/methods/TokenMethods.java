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

import javax.crypto.spec.SecretKeySpec;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
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

import fr.m2i.singleton.PrivateKey;

import com.nimbusds.jose.crypto.impl.ECDSA;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenMethods {
	
	//private PrivateKey key;
	
	
	public static String issueToken(String accessRight) {  
		
        try {
            
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
                       .subject(accessRight)
                       .expirationTime(Date.from(Instant.now().plusSeconds(600)))
                       //.issuer("localhost:4200")
                       .claim("test", 57)
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
            
        public static void validateToken(String token) throws JOSEException, ParseException { 
            //Validation
            /**ECKey key3 = new ECKeyGenerator(Curve.P_256)
                    .keyID("1")
                    .generate();**/
            ECKey newKey = PrivateKey.getInstance().getKey();
            Boolean isValid = SignedJWT.parse(token)
           	    .verify(new ECDSAVerifier(newKey.toECPublicKey()));
            
            //Decode
            if (isValid) {
            	SignedJWT decodedJWT = SignedJWT.parse(token);
                String header1 = decodedJWT.getHeader().toString();
                System.out.println(header1);
                String payload1 = decodedJWT.getPayload().toString();
                System.out.println(payload1);
            }
            System.out.println(isValid);
            
            /**
            ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<SimpleSecurityContext>();
            JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<SimpleSecurityContext>(key.toECPublicKey().getEncoded());
            JWEKeySelector<SimpleSecurityContext> jweKeySelector =
                new JWEDecryptionKeySelector<SimpleSecurityContext>(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
            jwtProcessor.setJWEKeySelector(jweKeySelector);
            **/
            
            /**
            String publicKeyContent =
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlPfD8thRbT1Md0xzA55zSzA084bevOGgFg1Jx5n2Fjtk5sJ" +
                            "piTKaWvw92gRoZm0F0UzJ+lo55CiUKWBHIR9y+FcsEotaWjjAx9llqFzbRkCc3x9TCKyCG0Pr6OwRZdAWYFTaEI7m" +
                            "eAfen+LuIUazwYBXfO7nVrzXg4EbMHL+wwUhalOJxkzBhXDOHnWKIQdNBSWUbl3RetWpGWYOzM9ePgGv2GbXgXFp4NYhS" +
                            "hqDewIAhG2KhJHFR4E10GLEOzKep6VhOX3dRH897QuSnud5c" +
                            "hoVrYePldzc2QGJYosgfn/oFfOTb+Kj4HQtOmvJvZZRfI7lWMjOgHen12vH8dOr0QIDAQAB";
            String jwt= "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJRcm5CVjYwVkZvZF9ZaEw2Um5QNXp1NGNXNWZra1VNZ2EwQWJrdmt0VVo0In0.eyJleHAiOjE2MTY0MjY1NzksImlhdCI6MTYxNjQyNTk3OSwianRpIjoiMDU4ZDNjYzItMGY5OC00ODBlLWI0NGEtM2ExOWUzNDQyNzkzIiwiaXNzIjoiaHR0cDovL3Nlc3Rhd2tjMDFkZXYuYXBpY2EubG9jYWw6ODA4MC9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJzdWIiOiIxMzI1YjM5Ny1mNzk4LTRmYTAtOGE2MC04OWRlM2MzZmQ0YmIiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhZG1pbi1jbGkiLCJzZXNzaW9uX3N0YXRlIjoiY2Y3YzYxN2MtNTFlMy00NGIzLTk0MmItMTk4NDA0NmNlNWNmIiwiYWNyIjoiMSIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiYWRtaW4ifQ.J76L_obm89kFAL7zwg5wt1Qni6r7GZYDdgbWCfEKZfKwDc95cOOnfX2ULvglRCJ-Noaq5JKmZgkyg0wRJKeny-9yEwu0KZJuuJXF4pVvjjjYQIY4o4f8XkXaMvZmR4Lvo-MXQr3yKSsSVfWte2rj4nvc_2COQId1e1YLCJR1h00eiahGCzao8UOizmQfMtBSP0V6waSCgi2LUqBGRtoP8xlRD3UD4w4wBS8_H72NXRSLBVHvJ7G6Qy3-yScnVIldibiqhNj5_htiFS7I32sQxLdNluoAXFy3SjkgcX7ibnaZTvE2l7Wn1izMaq3qVUV25FxCJrVpbbzyu8XAL7o0KQ";
            
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
            RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
            Object test = Jwts.parser()
                    .setSigningKey(pubKey)
                    .parseClaimsJws(jwt).getBody();
            System.out.println(test);
            
          **/  
            
            /**
            ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
            jwtProcessor.setJWSTypeVerifier(new DefaultJOSEObjectTypeVerifier<>(JOSEObjectType.JWT));
            
            JWKSource<SecurityContext> keySource=new RemoteJWKSet<>(new URL("https://demo.c2id.com/jwks.json"));
            JWSAlgorithm expectedJWSAlg = JWSAlgorithm.ES256;
            JWSKeySelector<SecurityContext> keySelector =new JWSVerificationKeySelector<>(expectedJWSAlg, keySource);
            jwtProcessor.setJWSKeySelector(keySelector);
            jwtProcessor.setJWTClaimsSetVerifier((JWTClaimsSetVerifier<SecurityContext>) new DefaultJWTClaimsVerifier<SecurityContext>(
            	    new JWTClaimsSet.Builder().issuer("https://demo.c2id.com").build(),
            	    new HashSet<>(Arrays.asList("sub", "exp"))));
            
         // Process the token
            SecurityContext ctx = null; // optional context parameter, not required here
            JWTClaimsSet claimsSet = jwtProcessor.process(jwtToken, ctx);

            // Print out the token claims set
            System.out.println(claimsSet.toJSONObject());**/
          
    }
	
	/**
	public static String decryptToken(String token) {
		 JsonObject payload = dao.getBearerDetails(user, pass);
		    String bearerToken =  JWebToken(payload).toString();

		    //@TODO Send this token over the network

		    //recieve the bearer token
		    JsonToken incomingToken = new JsonToken(bearerToken);

		    if (incomingToken.isValid()) {
		       System.out.println(incomingToken.getSubject());
		    }
		
	}**/

}
