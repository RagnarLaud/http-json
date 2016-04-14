package net.xeprione.http;

/**
 * This is a test class to show how easy it is to use the library.
 * It uses the HTTP protocol tester provided by httpbin.org
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Create the header
        HttpPostData data = new HttpPostData();
        data.setProperty("test", "testValue");
        data.setProperty("test2", "testValue2");
        data.setProperty("test3", "testValue3");
        HttpRequest request = HttpRequest.createPostRequest("http://httpbin.org/post", data);
        System.out.println(request);
        // Execute the request above
        HttpResponse response = request.execute();
        // Print out the response
        System.out.println(response);
    }

}
