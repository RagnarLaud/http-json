package net.xeprione.http.utils;

import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {

    public static String readLine(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        char c;

        while((c = (char) stream.read()) != '\n') builder.append(c);

        return builder.toString();
    }

}
