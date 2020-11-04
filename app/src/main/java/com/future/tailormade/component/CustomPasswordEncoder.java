package com.future.tailormade.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Value("${app.password.encoder.secret}")
    private String secret;

    @Value("${app.password.encoder.iteration}")
    private Integer iteration;

    @Value("${app.password.encoder.keylength}")
    private Integer keylength;

    @Override
    public String encode(CharSequence charSequence) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(
                            new PBEKeySpec(
                                    charSequence.toString().toCharArray(),
                                    secret.getBytes(),
                                    iteration,
                                    keylength
                            )
                    )
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean matches(CharSequence charSequence, String string) {
        return encode(charSequence).equals(string);
    }
}
