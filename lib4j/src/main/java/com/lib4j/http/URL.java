package com.lib4j.http;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class URL {

    public static List<NameValuePair> ToNameValuePair(Map<String, Object> params) throws Exception {
        return ToNameValuePair(params, false);
    }

    public static List<NameValuePair> ToNameValuePair(Map<String, Object> params, boolean encoding) throws Exception {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        List<NameValuePair> list = new ArrayList<>();
        for (String key : keys) {
            String value = params.get(key).toString();
            if (encoding) {
                value = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
            }
            list.add(new BasicNameValuePair(key, value));
        }
        return list;
    }

    public static String ToParams(Map<String, Object> params) throws Exception {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key).toString();
            value = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
            query.append(key);
            query.append("=");
            query.append(value);
            query.append("&");
        }
        if (query.length() > 0) {
            return query.toString().substring(0, query.length() - 1);
        }
        return "";

    }

}
