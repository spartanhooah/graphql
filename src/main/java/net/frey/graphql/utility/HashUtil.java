package net.frey.graphql.utility;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.bouncycastle.crypto.generators.OpenBSDBCrypt;

public class HashUtil {
    private static final String BCRYPT_SALT = "dontDoThisInProd";

    public static boolean doesBcryptMatch(String original, String hash) {
        return OpenBSDBCrypt.checkPassword(hash, original.getBytes(UTF_8));
    }

    public static String hashBcrypt(String original) {
        return OpenBSDBCrypt.generate(original.getBytes(UTF_8), BCRYPT_SALT.getBytes(UTF_8), 5);
    }
}
