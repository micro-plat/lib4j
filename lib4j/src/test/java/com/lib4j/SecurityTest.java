package com.lib4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lib4j.security.Des;
import com.lib4j.security.Des3;
import com.lib4j.security.Md5;
import com.lib4j.security.Rsa;

/**
 * Unit test for simple App.
 */
class SecurityTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testMd5() {
        String text = "12345678";
        String rst = "25d55ad283aa400af464c76d713c07ad";
        assertEquals(rst, Md5.encrypt(text));

    }

    @Test
    void testDes() {
        String text = "text";
        String pwd = "12345678";
        String rst = "qTn5jiJaJJU=";
        assertEquals(rst, Des.encrypt(text, pwd));
        assertEquals(text, Des.decrypt(rst, pwd));

    }

    @Test
    void testDes3() {
        String text = "text";
        String pwd = "12345678";
        String rst = "qTn5jiJaJJU=";
        assertEquals(rst, Des3.encrypt(text, pwd));
        assertEquals(text, Des3.decrypt(rst, pwd));

    }

    @Test
    void testRsa() {
        String text = "text";
        String rx = "";
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtkZ4flVMsiGNGwZtIZ7s2dG2e" +
                "DYrCK/VMarpfjvX/RqFC2Eq2AHUkMHFbDcBH3+eOujs2PJ3jEXdCBenP2CWRlch6" +
                "UB1QKr/FpZEAF3MT6eCJEqSNSolx0Rn5rkuSu1iPjGs2KgzYl1I5nwgWXzxOINyK" +
                "6MyjPHlHwPP4FmrXowIDAQAB";

        String priKey = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAK2Rnh+VUyyIY0bB" +
                "m0hnuzZ0bZ4NisIr9Uxqul+O9f9GoULYSrYAdSQwcVsNwEff5466OzY8neMRd0IF" +
                "6c/YJZGVyHpQHVAqv8WlkQAXcxPp4IkSpI1KiXHRGfmuS5K7WI+MazYqDNiXUjmf" +
                "CBZfPE4g3IrozKM8eUfA8/gWatejAgMBAAECgYEAg02Hm0jxlNzQjXRlLk8at0U4" +
                "r7p64A01EkwjFasmuakK0XCihDCwbWIgPsuWTEk7+SM1LACLSH2sBExI5q0TLWFM" +
                "ZWXwETy0VqLukJuS5Vp2OsCkGaULwD2rwknCsKfFQ9eKBSvkHct+Ync7fagwHYYi" +
                "pICE5A0ljefVA9sXAHECQQDl2EId2bTUSgJ1rQMIG4jBWbTxl3KzGCc50ia8TjJb" +
                "emxa7b+G91wY/cZCWGE96M1OXdhfm1FKQJPN3psDUXMlAkEAwVHzwat4p7df0qk9" +
                "Wyqi5Lh++ieaFbSeKUvaECvGNU9V4pyoefRbv4DwtG0XvvNpFCy/gtrbsI2Y2q+Z" +
                "8T4JJwJBAJbTiN+Jw24jeuW5uOmTF/S5Z1G6Llx0FxmMXDrpnYYGGiC5LxvkKp2D" +
                "upxC3tz/beplDt3+UCPHqLZJw7MzubkCQQC4sehVjtI+QibNaB2TfR+Vr2K48p72" +
                "v9IfmDVlH71Fb1+Zmpwnr7r8Ml9FsbhvGbS5rqrlhN1BR3bS79P+tIsfAkEAjuKx" +
                "8HxRH/7xsOEWLCWog6cpmfwzVkCAqjtqAmt6NixiUwD1cOpY12jDBKTyQH6A1msu" +
                "XYJTSCldh150CC33DA==";

        try {
            String rst = Rsa.encrypt(text, pubKey);
            rx = Rsa.decrypt(rst, priKey);
        } catch (Exception e) {

        }
        assertEquals(text, rx);

    }
}
