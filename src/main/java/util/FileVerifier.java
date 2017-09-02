package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tangyifeng on 2017/8/29.
 * Email: yifengtang_hust@outlook.com
 */
public class FileVerifier {

    private String lastFileCode;

    public boolean verify(String file) {
        String curFileCode = getCode(file);
        if (curFileCode == null) {
            return true;
        }
        if (lastFileCode == null) {
            lastFileCode = curFileCode;
            return true;
        } else {
            if (!curFileCode.equals(lastFileCode)) {
                lastFileCode = curFileCode;
                return false;
            } else {
                return true;
            }
        }
    }

    private String getCode(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream inputStream = new FileInputStream(file);
                MessageDigest digest = MessageDigest.getInstance("MD5");
                DigestInputStream digestInputStream = new DigestInputStream(inputStream, digest);
                int bufSize = 256 * 1024;
                byte[] buf = new byte[bufSize];
                while (digestInputStream.read(buf) > 0) ;
                digest = digestInputStream.getMessageDigest();
                byte[] resultBuf = digest.digest();
                return byteArrayToHex(resultBuf);
            } else {
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }
}
