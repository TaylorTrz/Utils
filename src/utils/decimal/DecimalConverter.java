package utils.decimal;


import org.apache.poi.hpsf.Decimal;

/**
 * 进制转换工具
 *
 * @author taoruizhe
 * @date 2020/03/13
 */
public class DecimalConverter {
    static class Binary {
        private long binary;

        public Binary(String str) {
            this.binary = Long.parseLong(str);
        }

        public Long getBinary() {
            return this.binary;
        }
    }

    /**
     * convert decimal to binary
     *
     * @param decimal
     * @return
     */
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
        for (int i = stb.length() - 1; i >= 0 ; i--) {
            dec = dec + (long) Math.pow(2, stb.length() - 1 - i) * (stb.charAt(i) - 48);
        }
        return dec;
    }


    public static void main(String[] args) {
        DecimalConverter converter = new DecimalConverter();
        System.out.println(converter.dec2Bin(4294967295L));
        System.out.println(converter.bin2Dec("11111111111111111111111111111111"));


    }
}
