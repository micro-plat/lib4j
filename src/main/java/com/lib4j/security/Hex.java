package com.lib4j.security;

public class Hex {
    private static String[] digits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static int int256 = 256;
    private static int int16 = 16;

    /**
     * @param buff
     * @return String
     */
    public static String encrypt(byte[] buff) {
        StringBuilder sb = new StringBuilder(int16);
        for (int i = 0; i < buff.length; i++) {
            sb.append(byteToHexString(buff[i]));
        }
        return sb.toString();
    }

    /**
     * @param b
     * @return String
     */
    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = int256 + n;
        }
        int d1 = n / int16;
        int d2 = n % int16;
        return digits[d1] + digits[d2];
    }
}
