package net.xeprione.http;

import java.io.IOException;
import java.net.Socket;

public class HttpRequest {

    private final HttpHeader header;
    private final byte[] data;

    public HttpRequest(HttpHeader header){
        this(header, new byte[0]);
    }

    public HttpRequest(HttpHeader header, byte[] data) {
        this.header = header;
        this.data = data;
    }

    public HttpResponse execute() throws IOException {
        Socket socket = new Socket(header.getUrl().getHostname(), header.getUrl().getPort());
        socket.getOutputStream().write(header.build());
        if (data.length > 0) socket.getOutputStream().write(data);

        return HttpResponse.read(socket.getInputStream());
    }

}
