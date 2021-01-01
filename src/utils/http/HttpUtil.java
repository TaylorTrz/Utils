package utils.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class HttpUtil {

    public void get(String url) {
        try {
            HttpGet request = new HttpGet("http://localhost:8080/cps/v1/servers/1/10?searchValue=&vpc_id=");
            request.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSS0hRaWhSWnBMWS1iQmlmRHdqSEdwSlJNU2JjTzdPQUZXODUzNUtnNlg4In0.eyJqdGkiOiI3OWRiNzlhOC1iMjNjLTQ4MzAtOTBlOS02ZWM1NzAwMzBkYjUiLCJleHAiOjE2MDc1MDk5NjQsIm5iZiI6MCwiaWF0IjoxNjA3NTA0NTY0LCJpc3MiOiJodHRwczovL2F1dGgtY2xvdWQtZGV2Lmluc3B1cmNsb3VkLmNuL2F1dGgvcmVhbG1zL3BpY3AiLCJhdWQiOlsiY2xpZW50LWluc3B1cnRlc3QwMSIsImNsaWVudC1pbnNwdXJ0ZXN0MDIiLCJhY2NvdW50Il0sInN1YiI6IjI1NjQzMmM5LWJlYzQtNGZjMi1iNjk1LWI1YTUyMDAxOTk5YSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImlhYXMtc2VydmVyIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZDRhN2M4NGQtMTg4NC00OWY5LTk0NGEtZWY5MmEwZTc4MjljIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJBQ0NPVU5UX0FETUlOIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImNsaWVudC1pbnNwdXJ0ZXN0MDEiOnsicm9sZXMiOlsic2hhcmUiXX0sImNsaWVudC1pbnNwdXJ0ZXN0MDIiOnsicm9sZXMiOlsiYWRtaW4iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInN2YyI6ImVjcyxzbGIiLCJ0cnVzdF9jbGllbnQiOiJ0cnVlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcm9qZWN0IjoiaW5zcHVydGVzdDAyIiwiZ3JvdXBzIjpbIi9ncm91cC1pbnNwdXJ0ZXN0MDIiXSwicHJlZmVycmVkX3VzZXJuYW1lIjoiaW5zcHVydGVzdDAyIiwibG9jYWxlIjoiemgtQ04iLCJlbWFpbCI6Imluc3B1cnRlc3QwMkBpbnNwdXIuY29tIn0.KZtHgLsyC1WINjNxPZ01rUoWGrmRnvZ8YWdBnigB7A7s-_li_NqvLOWuUnRA_2ViDKngYvRXsprcac4P9jdcPR23K0n7kbZ_odMECjDbmHFZFWkGjd_Z08NkFJq1KdPzMCeqfUNZwvKalkgbwJ5zbe2IqNca-ROwnV3MK-ijA-6_zl089vqQBnyBHfNKZeUfA189z30C7UrSik4agy4oY1laC5rGOae_olnd0IEkPfZ0ldtJx3CH-lR_njua5WQ_xswECpWZwhB6kRhwsr8t7lI90ZKdZdWNesoc56oGZgIL3Z0hU0WVg1d9wj_wzUgyPGQPXEGYpycDcbdzkCzllQ");
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // 模拟多用户同时点击列表接口，测试列表接口的 并发能力
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> new HttpUtil().get("")).start();
        }
    }


}
