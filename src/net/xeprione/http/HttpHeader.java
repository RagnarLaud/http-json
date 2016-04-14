package net.xeprione.http;

import java.util.HashMap;

public class HttpHeader {

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

    private final HashMap<HeaderField, String> fields = new HashMap<>();

    private Method method;
    private RequestURL url;
    private Version version;

    public HttpHeader(Method method, RequestURL url) {
        this(method, url, Version.HTTP11);
    }

    public HttpHeader(Method method, RequestURL url, Version version) {
        this.method = method;
        this.url = url;
        this.version = version;

        setField(HeaderField.UserAgent, "HTTP-JSON");
        setField(HeaderField.Host, url.getHost());
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

    public void setField(HeaderField HeaderField, Object value) {
        fields.put(HeaderField, value.toString());
    }

    public byte[] build() {
        return toString().concat("\n").getBytes();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append(String.format("%s %s %s", method, url.getFilename(), version)).append("\n");

        for (HeaderField HeaderField : fields.keySet()) {
            String key = fields.get(HeaderField);
            string.append(HeaderField).append(": ").append(key).append("\n");
        }

        return string.toString();
    }
}
