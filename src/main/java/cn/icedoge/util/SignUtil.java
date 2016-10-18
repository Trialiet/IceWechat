package cn.icedoge.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by Trialiet on 2016/10/12.
 */
public class SignUtil {
    private static final String TOKEN = "wechat";
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static boolean check(String signature, Long timestamp, Long nonce){
        String[] temp = {TOKEN, timestamp+"", nonce+""};
        Arrays.sort(temp);
        String str = temp[0] + temp[1] + temp[2];
        String result = encrypt(str);
        if (result.equals(signature.toLowerCase())){
            return true;
        }
        return false;
    }

    private static String encrypt(String s){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes("UTF-8"));
//          byte[] result = digest.digest();
//          StringBuffer stringBuffer = new StringBuffer();
//            for (byte b : result){
//                int i = b & 0xff;
//                if (i < 0xf){
//                    stringBuffer.append(0);
//                }
//                stringBuffer.append(Integer.toHexString(i));
//            }
//          return stringBuffer.toString().toLowerCase();
            return getFormattedText(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString().toLowerCase();
    }
}
