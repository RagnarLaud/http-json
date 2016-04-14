package net.xeprione.http;

import java.util.HashMap;

public class ResponseHeader {

    private final HashMap<HeaderField, String> fields = new HashMap<>();

    private final HttpHeader.Version version;
    private final int status;
    private final String statusMessage;

    private ResponseHeader(HttpHeader.Version version, int status, String statusMessage) {
        this.version = version;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public static ResponseHeader create(String status){
        String[] foo = status.split(" ", 3);

        HttpHeader.Version version = HttpHeader.Version.HTTP11;
        int statusCode = foo[1].trim().matches("\\d+") ? Integer.parseInt(foo[1].trim()) : -1;
        String statusMessage = foo[2].trim();
        return new ResponseHeader(version, statusCode, statusMessage);
    }

    public void setField(HeaderField field, String value) {
        fields.put(field, value);
    }

    public String getField(String field){
        return getField(HeaderField.find(field));
    }

    public String getField(String field, String defaultValue){
        return getField(HeaderField.find(field), defaultValue);
    }

    public String getField(HeaderField field){
        return getField(field, "");
    }

    public String getField(HeaderField field, String defaultValue) {
        return fields.getOrDefault(field, defaultValue);
    }

    public void setField(String string) {
        String[] foo = string.split(":");
        if (foo.length >= 2) {
            HeaderField field = HeaderField.find(foo[0].trim());
            String value = foo[1].trim();
            setField(field, value);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s %s %s", version, status, statusMessage)).append("\n");

        for(HeaderField field : fields.keySet()){
            builder.append(field).append(": ").append(fields.get(field)).append("\n");
        }

        return builder.toString();
    }
}
