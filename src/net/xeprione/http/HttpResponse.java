package net.xeprione.http;

import net.xeprione.http.utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

public class HttpResponse {

    private ResponseHeader header;
    private byte[] message;

    public HttpResponse(ResponseHeader header) {
        this(header, new byte[0]);
    }

    public HttpResponse(ResponseHeader header, byte[] message) {
        this.header = header;
        this.message = message;
    }

    public ResponseHeader getHeader() {
        return header;
    }

    public static HttpResponse read(InputStream stream) throws IOException {
        int limit = 16 * 1000;
        int i = 0;

        String status = StreamUtils.readLine(stream);
        ResponseHeader header = ResponseHeader.create(status);

        do {
            String line = StreamUtils.readLine(stream).trim();
            if (line.isEmpty())
                break;
            header.setField(line);
        } while (++i < limit);

        if (i >= limit) {
            throw new RuntimeException("ERR_HEADER_TOO_BIG");
        }

        byte[] message = new byte[Integer.parseInt(header.getField("Content-Length", "0"))];
        int length = stream.read(message);

        return new HttpResponse(header, message);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(header).append("\n").append(new String(message));

        return builder.toString();
    }
}
