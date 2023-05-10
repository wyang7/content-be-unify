package com.resource.wangyang.cbu.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5{
    private static final Charset charset = Charset.forName("utf-8");
    private static ThreadLocal<MessageDigest> md5Local = new ThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    };
    private static final char[] HEX_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static MessageDigest instance(){
        return md5Local.get();
    }

    public static byte[] digest(String string) {
        MessageDigest md5 = md5Local.get();
        return md5.digest(string.getBytes(charset));
    }

    public static byte[] digest(byte[] inStr) {
        MessageDigest md5 = md5Local.get();
        return md5.digest(inStr);
    }

    public static byte[] digest(byte[] inStr, int offset, int len) {
        MessageDigest md5 = md5Local.get();
        md5.update(inStr, offset, len);
        return md5.digest();
    }

    public static byte[] digest(File file) throws Exception{
        try(InputStream input = new FileInputStream(file)){
            MessageDigest md5 = md5Local.get();
            byte[] buf = new byte[1024 * 4];
            for(int len; (len = input.read(buf)) > 0;){
                md5.update(buf, 0, len);
            }
            return md5.digest();
        }
    }

    public static String md5file(File file) throws Exception{
        return toHex(digest(file));
    }

    public static String md5String(String string){
        return toHex(digest(string));
    }

    public static String toHex(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static byte[] fromHex(String str){
        int len = str.length();
        byte[] bytes = new byte[len / 2];
        for(int i=0,j=0;i<len;i+=2,j++){
            int c0 = Arrays.binarySearch(HEX_DIGITS, str.charAt(i));
            int c1 = Arrays.binarySearch(HEX_DIGITS, str.charAt(i + 1));
            int b = (c0 << 4) | c1;
            bytes[j] = (byte)(b & 0xff);
        }
        return bytes;
    }

}
