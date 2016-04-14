package net.xeprione.http;

import java.util.HashMap;
import java.util.Iterator;

public class HttpPostData implements HttpData {

    private final HashMap<String, String> properties = new HashMap<>();

    public void setProperty(String key, String val) {
        properties.put(key, val);
    }

    public int size(){
        return build().length;
    }

    @Override
    public byte[] build() {
        return toString().getBytes();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Iterator<String> iterator = properties.keySet().iterator();

        while(iterator.hasNext()){
            String key = iterator.next();
            String val = properties.get(key);
            builder.append(key).append("=").append(val);
            if(iterator.hasNext()) builder.append("&");
        }

        return builder.toString();
    }
}
