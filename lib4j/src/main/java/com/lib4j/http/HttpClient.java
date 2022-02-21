package com.lib4j.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClient {
    private int socketTimeout = 30000;
    private int connectTimeout = 30000;

    public HttpClient() {

    }

    public HttpClient(int socketTimeout, int connectTimeout) {
        this.socketTimeout = socketTimeout;
        this.connectTimeout = connectTimeout;
    }

    // 发送GET请求
    public Response get(String url) {
        return get(url, null);
    }

    // 发送GET请求
    public Response get(String url, List<NameValuePair> parametersBody) {
        try {
            if (parametersBody == null) {
                parametersBody = new ArrayList<>();
            }
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setParameters(parametersBody);
            HttpGet get = new HttpGet(uriBuilder.build());
            get.setConfig(
                    RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build());
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            get.releaseConnection();
            return new Response(code, EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            return new Response(600, "ERR:远程请求失败" + e.getMessage());
        }
    }

    // 发送POST请求（普通表单形式）
    public Response postForm(String url, List<NameValuePair> parametersBody) {
        HttpEntity entity = new UrlEncodedFormEntity(parametersBody, Charsets.UTF_8);
        return post(url, "application/x-www-form-urlencoded", entity);
    }

    // 发送POST请求（JSON形式）
    public Response postJSON(String url, String json) {
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        return post(url, "application/json", entity);
    }

    // 发送POST请求
    public Response post(String url, String mediaType, HttpEntity entity) {

        try {
            HttpPost post = new HttpPost(url);
            post.setConfig(
                    RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build());
            post.addHeader("Content-Type", mediaType);
            post.addHeader("Accept", "application/json");
            post.setEntity(entity);
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(post);
            post.releaseConnection();
            int code = response.getStatusLine().getStatusCode();
            return new Response(code, EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            return new Response(600, "ERR:远程请求失败" + e.getMessage());
        }
    }
}
