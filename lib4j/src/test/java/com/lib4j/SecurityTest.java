package com.lib4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lib4j.security.Aes;
import com.lib4j.security.Des;
import com.lib4j.security.Des3;
import com.lib4j.security.Md5;
import com.lib4j.security.Rsa;
import com.lib4j.security.SHA;

/**
 * Unit test for simple App.
 */
class SecurityTest {

    @Test
    void testSHA1() {
        String text = "12345678";
        String rst = "7c222fb2927d828af22f592134e8932480637c0d";
        assertEquals(rst, SHA.sha1(text));

    }

    @Test
    void testSHA256() {
        String text = "12345678";
        String rst = "ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f";
        assertEquals(rst, SHA.sha256(text));

    }

    // @Test
    // void testSHA512() {
    //     String text = "12345678";
    //     String rst = "32d938bec236b8d25ac5af4404f3f916";
    //     assertEquals(rst, SHA.sha512(text));

    // }

    @Test
    void testAES() throws Exception {
        String text = "12345678";
        String key = "1234567890123456";
        String rst = "WH6EuTqFiII8I8/lcEvP2w==";
        assertEquals(rst, Aes.Instance.encrypt(text, key));
        assertEquals(text, Aes.Instance.decrypt(rst, key));
    }

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
    void testDes() throws Exception {
        String text = "text";
        String pwd = "12345678";
        String rst = "qTn5jiJaJJU=";
        assertEquals(rst, Des.Instance.encrypt(text, pwd));
        assertEquals(text, Des.Instance.decrypt(rst, pwd));

    }

    // @Test
    // void testDes3() throws Exception {
    //     String text = "text";
    //     String pwd = "12345678";
    //     String rst = "qTn5jiJaJJU=";
    //     assertEquals(rst, Des3.Instance.encrypt(text, pwd));
    //     assertEquals(text, Des3.Instance.decrypt(rst, pwd));

    // }

    @Test
    void testRsa() throws Exception {
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

        String rst = Rsa.encrypt(text, pubKey);
        rx = Rsa.decrypt(rst, priKey);

        assertEquals(text, rx);

    }
}
