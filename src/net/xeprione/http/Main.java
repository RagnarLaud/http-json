package net.xeprione.http;

/**
 * This is a test class to show how easy it is to use the library.
 * It uses the HTTP protocol tester provided by httpbin.org
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Create the header
        HttpHeader header = new HttpHeader(HttpHeader.Method.GET, new RequestURL("http://httpbin.org/ip"));
        // Print out the header
        System.out.println(header);
        // Create a request and execute it
        HttpResponse response = new HttpRequest(header).execute();
        // Print out the response
        System.out.println(response);
    }
}
