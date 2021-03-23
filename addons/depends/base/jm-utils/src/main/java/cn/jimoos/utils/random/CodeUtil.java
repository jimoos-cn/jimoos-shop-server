package cn.jimoos.utils.random;

import java.util.Random;

/**
 * Des:生成唯一码
 *
 * @author chenqisheng
 * @date : 14-3-29 下午1:23
 */
public class CodeUtil {
    private CodeUtil() {
    }

    public static final int INVITE_CODE_LENGTH = 6;
    public static final int SMS_CODE_LENGTH = 6;

    public static String genRamCode(int strLength) {
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'z', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        return genString(strLength, str);
    }

    public static String genRamNumber(int strLength) {
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        return genString(strLength, str);
    }

    private static String genString(int strLength, char[] str) {
        int maxNum = str.length;
        int i;
        int count = 0;
        StringBuilder code = new StringBuilder("");
        Random r = new Random();
        while (count < strLength) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                code.append(str[i]);
                count++;
            }
        }
        return code.toString();
    }

    public static String genInviteCode() {
        return genRamCode(INVITE_CODE_LENGTH);
    }

    public static String genSmsCode() {
        return genRamNumber(SMS_CODE_LENGTH);
    }
}
