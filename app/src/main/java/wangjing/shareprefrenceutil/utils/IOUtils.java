//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package wangjing.shareprefrenceutil.utils;

import java.io.Closeable;

public class IOUtils {
    public static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final boolean[] firstIdentifierFlags = new boolean[256];
    public static final boolean[] identifierFlags;
    public static final byte[] specicalFlags_doubleQuotes;
    public static final byte[] specicalFlags_singleQuotes;
    public static final char[] replaceChars;
    public static final char[] ASCII_CHARS;
    static final char[] digits;
    static final char[] DigitTens;
    static final char[] DigitOnes;
    static final int[] sizeTable;

    public IOUtils() {
    }

    public static void close(Closeable x) {
        if(x != null) {
            try {
                x.close();
            } catch (Exception var2) {
                ;
            }
        }

    }

    public static int stringSize(long x) {
        long p = 10L;

        for(int i = 1; i < 19; ++i) {
            if(x < p) {
                return i;
            }

            p = 10L * p;
        }

        return 19;
    }

    public static void getChars(long i, int index, char[] buf) {
        int charPos = index;
        byte sign = 0;
        if(i < 0L) {
            sign = 45;
            i = -i;
        }

        int r;
        while(i > 2147483647L) {
            long q = i / 100L;
            r = (int)(i - ((q << 6) + (q << 5) + (q << 2)));
            i = q;
            --charPos;
            buf[charPos] = DigitOnes[r];
            --charPos;
            buf[charPos] = DigitTens[r];
        }

        int q2;
        int i2;
        for(i2 = (int)i; i2 >= 65536; buf[charPos] = DigitTens[r]) {
            q2 = i2 / 100;
            r = i2 - ((q2 << 6) + (q2 << 5) + (q2 << 2));
            i2 = q2;
            --charPos;
            buf[charPos] = DigitOnes[r];
            --charPos;
        }

        do {
            q2 = i2 * '쳍' >>> 19;
            r = i2 - ((q2 << 3) + (q2 << 1));
            --charPos;
            buf[charPos] = digits[r];
            i2 = q2;
        } while(q2 != 0);

        if(sign != 0) {
            --charPos;
            buf[charPos] = (char)sign;
        }

    }

    public static int stringSize(int x) {
        int i;
        for(i = 0; x > sizeTable[i]; ++i) {
            ;
        }

        return i + 1;
    }

    static {
        char i;
        for(i = 0; i < firstIdentifierFlags.length; ++i) {
            if(i >= 65 && i <= 90) {
                firstIdentifierFlags[i] = true;
            } else if(i >= 97 && i <= 122) {
                firstIdentifierFlags[i] = true;
            } else if(i == 95) {
                firstIdentifierFlags[i] = true;
            }
        }

        identifierFlags = new boolean[256];

        for(i = 0; i < identifierFlags.length; ++i) {
            if(i >= 65 && i <= 90) {
                identifierFlags[i] = true;
            } else if(i >= 97 && i <= 122) {
                identifierFlags[i] = true;
            } else if(i == 95) {
                identifierFlags[i] = true;
            } else if(i >= 48 && i <= 57) {
                identifierFlags[i] = true;
            }
        }

        specicalFlags_doubleQuotes = new byte[256];
        specicalFlags_singleQuotes = new byte[256];
        replaceChars = new char[128];
        specicalFlags_doubleQuotes[0] = 4;
        specicalFlags_doubleQuotes[1] = 4;
        specicalFlags_doubleQuotes[2] = 4;
        specicalFlags_doubleQuotes[3] = 4;
        specicalFlags_doubleQuotes[4] = 4;
        specicalFlags_doubleQuotes[5] = 4;
        specicalFlags_doubleQuotes[6] = 4;
        specicalFlags_doubleQuotes[7] = 4;
        specicalFlags_doubleQuotes[8] = 1;
        specicalFlags_doubleQuotes[9] = 1;
        specicalFlags_doubleQuotes[10] = 1;
        specicalFlags_doubleQuotes[11] = 4;
        specicalFlags_doubleQuotes[12] = 1;
        specicalFlags_doubleQuotes[13] = 1;
        specicalFlags_doubleQuotes[34] = 1;
        specicalFlags_doubleQuotes[92] = 1;
        specicalFlags_singleQuotes[0] = 4;
        specicalFlags_singleQuotes[1] = 4;
        specicalFlags_singleQuotes[2] = 4;
        specicalFlags_singleQuotes[3] = 4;
        specicalFlags_singleQuotes[4] = 4;
        specicalFlags_singleQuotes[5] = 4;
        specicalFlags_singleQuotes[6] = 4;
        specicalFlags_singleQuotes[7] = 4;
        specicalFlags_singleQuotes[8] = 1;
        specicalFlags_singleQuotes[9] = 1;
        specicalFlags_singleQuotes[10] = 1;
        specicalFlags_singleQuotes[11] = 4;
        specicalFlags_singleQuotes[12] = 1;
        specicalFlags_singleQuotes[13] = 1;
        specicalFlags_singleQuotes[92] = 1;
        specicalFlags_singleQuotes[39] = 1;

        int var1;
        for(var1 = 14; var1 <= 31; ++var1) {
            specicalFlags_doubleQuotes[var1] = 4;
            specicalFlags_singleQuotes[var1] = 4;
        }

        for(var1 = 127; var1 <= 160; ++var1) {
            specicalFlags_doubleQuotes[var1] = 4;
            specicalFlags_singleQuotes[var1] = 4;
        }

        replaceChars[0] = 48;
        replaceChars[1] = 49;
        replaceChars[2] = 50;
        replaceChars[3] = 51;
        replaceChars[4] = 52;
        replaceChars[5] = 53;
        replaceChars[6] = 54;
        replaceChars[7] = 55;
        replaceChars[8] = 98;
        replaceChars[9] = 116;
        replaceChars[10] = 110;
        replaceChars[11] = 118;
        replaceChars[12] = 102;
        replaceChars[13] = 114;
        replaceChars[34] = 34;
        replaceChars[39] = 39;
        replaceChars[47] = 47;
        replaceChars[92] = 92;
        ASCII_CHARS = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
        digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        DigitTens = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
        DigitOnes = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        sizeTable = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, 2147483647};
    }
}
