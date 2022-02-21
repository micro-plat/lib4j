package com.lib4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lib4j.http.HttpClient;
import com.lib4j.http.Response;

import org.junit.jupiter.api.Test;

public class HttpTest {
    @Test
    void testHttp() {

        HttpClient client = new HttpClient();
        Response response = client.get("https://www.baidu.com");
        assertEquals(200, response.GetStatus());
    }
}
