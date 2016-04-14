package net.xeprione.http;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestURL {

    private String protocol;
    private String hostname;
    private String filename;
    private int port;

    public RequestURL(String URL) throws MalformedURLException {
        protocol    = "";
        hostname    = "";
        filename    = "";
        port        = -1;

        String url = URL;

        if (url.matches("(.+)://(.+)")) {
            Pattern p = Pattern.compile("(.+)://");
            Matcher matcher = p.matcher(url);
            if (matcher.find())
                protocol = matcher.group();
            url = url.substring(protocol.length());
        }else{
            protocol = "http://";
        }

        if (url.matches("(.+):(\\d+)?(.+)")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                String g = matcher.group();
                int i = url.indexOf(g);
                hostname = url.substring(0, i-1);
                port = Integer.parseInt(g);
            }

            int offset = hostname.length() + String.valueOf(port).length() + 1;
            filename = url.substring(offset);
        }else if(url.matches("(.+)?(/(.+))")){
            String[] a = url.split("/", 2);
            port = 80;
            hostname = a[0];
            filename = "/".concat(a[1]);
        }else{
            hostname = url;
        }

        if(protocol == null || protocol.isEmpty()) protocol = "http://";
        if(hostname == null || hostname.isEmpty()) throw new MalformedURLException("Missing hostname");
        if(filename == null || filename.isEmpty()) filename = "/";
        if(port < 0) port = 80;
    }

    public String getHostname() {
        return hostname;
    }

    public String getHost(){
        return String.format("%s:%s", hostname, port);
    }

    public String getProtocol() {
        return protocol;
    }

    public String getFilename() {
        return filename;
    }

    public int getPort() {
        return port;
    }
}
