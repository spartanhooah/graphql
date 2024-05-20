package net.frey.graphql.utility;

import java.nio.charset.StandardCharsets;
import org.bouncycastle.crypto.generators.OpenBSDBCrypt;

public class HashUtil {
    public static boolean doesBcryptMatch(String original, String hash) {
        return OpenBSDBCrypt.checkPassword(hash, original.getBytes(StandardCharsets.UTF_8));
    }
}
