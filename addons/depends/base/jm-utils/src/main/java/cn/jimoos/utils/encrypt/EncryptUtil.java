package cn.jimoos.utils.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * md5&sha512
 *
 * @author keepcleargas
 * @date 2021-03-23
 */
@Slf4j
public class EncryptUtil {
    public static String sha512(String str) {
        try {
            return sha512(str.getBytes("UTF-8"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static String sha512(byte[] arr) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-512");
            sha.reset();
            // must specify "UTF-8" encoding
            sha.update(arr);
            byte[] array = sha.digest();
            // Use Base64 encoding here
            String hashed = Base64.encodeBase64URLSafeString(array);
            StringBuilder sb = new StringBuilder(32);
            if (hashed.length() > 32) {
                sb.append(hashed.substring(0, 32));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static String md5(String str) {
        try {
            return md5(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static String md5(byte[] arr) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            // must specify "UTF-8" encoding
            md.update(arr);
            byte[] array = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
