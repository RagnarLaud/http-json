package net.xeprione.http;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpHeader header = new HttpHeader(HttpHeader.Method.GET, new RequestURL("http://httpbin.org/ip"));
        System.out.println(header);
        HttpRequest request = new HttpRequest(header);
        HttpResponse response = request.execute();
        System.out.println(response);
    }
}
