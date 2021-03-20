package utils.decimal;


/**
 * 进制转换工具
 *
 * @author taoruizhe
 * @date 2020/03/13
 */
public class DecimalConverter {

    //-----------------------------
    // convert decimal to binary
    // 十进制转换二进制
    //------------------------------
    public String dec2Bin(long decimal) {
        StringBuilder stb = new StringBuilder();
        while (decimal != 0) {
            long quotient = (long) Math.floor(decimal / 2);
            stb.append(decimal - quotient * 2);
            decimal = quotient;
        }
        return stb.reverse().toString();
    }


    public long bin2Dec(String bin) {
        long dec = 0L;
        StringBuilder stb = new StringBuilder(bin);
        for (int i = stb.length() - 1; i >= 0; i--) {
            dec = dec + (long) Math.pow(2, stb.length() - 1 - i) * (stb.charAt(i) - 48);
        }
        return dec;
    }


    //-----------------------------
    // convert decimal to octal
    // 十进制转换八进制
    //------------------------------
    public String dec2Oct(long dec) {
        StringBuilder oct = new StringBuilder();
        while (dec != 0) {
            long quotient = (long) Math.floor(dec / 8);
            oct.append(dec - quotient * 8);
            dec = quotient;
        }
        return oct.reverse().toString();
    }


    public long oct2Dec(String oct) {
        long dec = 0L;
        StringBuilder stb = new StringBuilder(oct);
        for (int i = stb.length() - 1; i >= 0; i--) {
            dec = dec + (long) Math.pow(8, stb.length() - 1 - i) * (stb.charAt(i) - 48);
        }
        return dec;
    }


    //-----------------------------
    // convert decimal to hexadecimal
    // 十进制转换十六进制
    //------------------------------
    public String dec2Hex(long dec) {
        StringBuilder hex = new StringBuilder();
        while (dec != 0) {
            long quotient = (long) Math.floor(dec / 16);
            int res = (int) (dec - quotient * 16);
            if (res < 10) {
                hex.append(res);
            } else {
                hex.append(Character.toChars(res + 55));
            }
            dec = quotient;
        }
        return hex.reverse().toString();
    }

    public long hex2Dec(String hex) {
        long dec = 0L;
        StringBuilder stb = new StringBuilder(hex);
        for (int i = stb.length() - 1; i >= 0; i--) {
            if (stb.charAt(i) < 16) {
                dec = dec + (long) Math.pow(16, stb.length() - 1 - i) * (stb.charAt(i) - 48);
            } else {
                dec = dec + (long) Math.pow(16, stb.length() - 1 - i) * (stb.charAt(i) - 55);
            }
        }
        return dec;
    }


    public static void main(String[] args) {
        DecimalConverter converter = new DecimalConverter();
        System.out.println(converter.dec2Bin(4294967295L));
        System.out.println(converter.bin2Dec("11111111111111111111111111111111"));
        System.out.println(converter.dec2Oct(25L));
        System.out.println(converter.dec2Hex(15));
        System.out.println(converter.hex2Dec("FF"));
    }
}
