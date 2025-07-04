package jp.co.aforce.api;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;

public class SRPVerifierGenerator {

    // RFC 5054: 1024-bit safe prime (N), generator (g)
    private static final String N_HEX =
        "EEAF0AB9ADB38DD69C33F80AFA8FC5E8607261877519"
      + "55FFB587DA55D6F834566E3025E316A330EFBB77F23"
      + "B9C5B2A2ECEF3B52C6B8A2123D7F7C4FEF663F4860EE"
      + "12BF2D5B0B7474D6E694F91E6DCC4024FFFFFFFFFFFFFFFF";
    private static final BigInteger N = new BigInteger(N_HEX, 16);
    private static final BigInteger g = BigInteger.valueOf(2);

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String username = "user01";
        String password = "password123";
        String saltStr = "SALT001"; // DBと一致させること

        // saltバイト列を明示的に生成しHex表示も対応
        byte[] saltBytes = saltStr.getBytes(StandardCharsets.UTF_8);
        BigInteger salt = new BigInteger(1, saltBytes); // 正の数として解釈

        String identity = username + ":" + password;

        // Step 1: x = H(salt | H(I | ":" | P))
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashIP = digest.digest(identity.getBytes(StandardCharsets.UTF_8));
        byte[] saltPlusHash = concat(saltBytes, hashIP);  // toByteArray()ではなく元のsaltBytesを使用
        byte[] xHash = digest.digest(saltPlusHash);
        BigInteger x = new BigInteger(1, xHash);

        // Step 2: v = g^x % N
        BigInteger v = g.modPow(x, N);

        // 出力（DBに入れるべき形式）
        System.out.println("【保存用】salt (hex)     : " + Hex.toHexString(saltBytes)); // ← DBへこのまま
        System.out.println("【保存用】verifier (hex) : " + v.toString(16));           // ← DBへこのまま

        // デバッグ用
        System.out.println("salt (as int)    : " + salt);
        System.out.println("x (private key)  : " + x.toString(16));
    }

    private static byte[] concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
