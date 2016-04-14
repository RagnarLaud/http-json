package net.xeprione.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;

public class HttpRequest {

    private HttpHeader header;
    private HttpData data;

    public HttpRequest(HttpHeader header){
        this(header, null);
    }

    public HttpRequest(HttpHeader header, HttpData data) {
        this.header = header;
        this.data = data;
    }

    public HttpResponse execute() throws IOException {
        Socket socket = new Socket(header.getUrl().getHostname(), header.getUrl().getPort());
        socket.getOutputStream().write(header.build());
        if(data != null) {
            socket.getOutputStream().write(data.build());
        }

        return HttpResponse.read(socket.getInputStream());
    }

    public static HttpRequest createGetRequest(RequestURL url){
        HttpHeader header = new HttpHeader(HttpHeader.Method.GET, url);
        return new HttpRequest(header);
    }

    public static HttpRequest createPostRequest(String url, HttpPostData data) throws MalformedURLException {
        return createPostRequest(new RequestURL(url), data);
    }

    public static HttpRequest createPostRequest(RequestURL url, HttpPostData data){
        HttpHeader header = new HttpHeader(HttpHeader.Method.POST, url);
        header.setField(HeaderField.ContentType, "application/x-www-form-urlencoded");
        header.setField(HeaderField.ContentLength, data.size());
        return new HttpRequest(header, data);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(header).append("\n").append(data).toString();
    }
}
