package com.study.demo.common;

import com.google.crypto.tink.*;
import com.google.crypto.tink.config.TinkConfig;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Base64;

@Component("tokenCryptoService")
public class TokenCryptoService {

    private final Aead aead;
    private final String keyId = System.getenv().getOrDefault("GITHUB_TOKEN_KEY_ID", "v1");

    public TokenCryptoService() throws Exception {
        TinkConfig.register();

        KeysetHandle handle = CleartextKeysetHandle.read(
                JsonKeysetReader.withInputStream(new FileInputStream(
                        Path.of(System.getenv().getOrDefault(
                                "GITHUB_TOKEN_KEYSET_PATH",
                                "secrets/github.aead.json"
                        )).toFile()
                )));

        this.aead = handle.getPrimitive(RegistryConfiguration.get(), Aead.class);
    }

    public String encrypt(String plainText, String data) throws Exception {
        byte[] ct = aead.encrypt(
                plainText.getBytes(StandardCharsets.UTF_8),
                associatedData(data)
        );

        return keyId + ":" + Base64.getEncoder().encodeToString(ct);
    }

    public String decrypt(String stored, String data) throws Exception {
        String[] parts = stored.split(":", 2);
        String ctb64 = parts.length == 2 ? parts[1] : parts[0];

        byte[] ct = Base64.getDecoder().decode(ctb64);
        byte[] pt = aead.decrypt(ct, associatedData(data));
        return new String(pt, StandardCharsets.UTF_8);
    }

    private byte[] associatedData(String data) {
        return data == null ? new byte[0] : data.getBytes(StandardCharsets.UTF_8);
    }
}
