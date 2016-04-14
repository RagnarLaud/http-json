package net.xeprione.http;

import java.util.HashMap;

public class HttpHeader {

    public enum Field {
        Accept("Accept"),
        AcceptCharset("Accept-Charset"),
        AcceptEncoding("Accept-Encoding"),
        AcceptLanguage("Accept-Language"),
        Authorization("Authorization"),
        Expect("Expect"),
        From("From"),
        Host("Host"),
        IfMatch("If-Match"),
        IfModifiedSince("If-Modified-Since"),
        IfNoneMatch("If-None-Match"),
        IfRange("If-Range"),
        IfUnmodifiedSince("If-Unmodified-Since"),
        MaxForwards("Max-Forwards"),
        ProxyAuthorization("Proxy-Authorization"),
        Range("Range"),
        Referer("Referer"),
        TE("TE"),
        UserAgent("User-Agent");

        private final String field;

        Field(String header) {
            this.field = header;
        }

        public String getField() {
            return field;
        }

        @Override
        public String toString() {
            return getField();
        }
    }

    public enum Method {
        OPTIONS, GET, HEAD, POST, PUT, DELETE
    }

    public enum Version {
        HTTP10("HTTP/1.0"), HTTP11("HTTP/1.1"), HTTP2("HTTP/2");

        private final String version;

        Version(String version) {
            this.version = version;
        }

        public String getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return getVersion();
        }
    }

    private Method method;
    private RequestURL url;
    private Version version;
    private final HashMap<Field, String> fields = new HashMap<>();

    public HttpHeader(Method method, RequestURL url) {
        this(method, url, Version.HTTP11);
    }

    public HttpHeader(Method method, RequestURL url, Version version) {
        this.method = method;
        this.url = url;
        this.version = version;

        setField(Field.UserAgent, "HTTP-JSON");
        setField(Field.Host, url.getHost());
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public RequestURL getUrl() {
        return url;
    }

    public void setUrl(RequestURL url) {
        this.url = url;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void setField(Field field, String value) {
        fields.put(field, value);
    }

    public byte[] build() {
        return toString().concat("\n").getBytes();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append(String.format("%s %s %s", method, url.getFilename(), version)).append("\n");

        for (Field field : fields.keySet()) {
            String key = fields.get(field);
            string.append(field).append(": ").append(key).append("\n");
        }

        return string.toString();
    }
}
