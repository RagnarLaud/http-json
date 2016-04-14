package net.xeprione.http;

enum HeaderField {
    Accept("Accept"),
    AcceptCharset("Accept-Charset"),
    AcceptEncoding("Accept-Encoding"),
    AcceptLanguage("Accept-Language"),
    AcceptDatetime("Accept-Datetime"),
    Authorization("Authorization"),
    Cookie("Cookie"),
    Expect("Expect"),
    Forwarded("Forwarded"),
    From("From"),
    Host("Host"),
    IfMatch("If-Match"),
    IfModifiedSince("If-Modified-Since"),
    IfNoneMatch("If-None-Match"),
    IfRange("If-Range"),
    IfUnmodifiedSince("If-Unmodified-Since"),
    MaxForwards("Max-Forwards"),
    Origin("Origin"),
    ProxyAuthorization("Proxy-Authorization"),
    Range(" Range"),
    Referer("Referer "),
    TE("TE"),
    UserAgent("User-Agent"),
    XRequestedWith("X-Requested-With"),
    DNT("DNT"),
    XForwardedFor("X-Forwarded-For"),
    XForwardedHost("X-Forwarded-Host"),
    XForwardedProto("X-Forwarded-Proto"),
    FrontEndHttps("Front-End-Https"),
    XHttpMethodOverride("X-Http-Method-Override"),
    XATTDeviceId("X-ATT-DeviceId"),
    XWapProfile("X-Wap-Profile"),
    ProxyConnection("Proxy-Connection"),
    XUIDH("X-UIDH"),
    XCsrfToken("X-Csrf-Token"),
    AccessControlAllowOrigin("Access-Control-Allow-Origin"),
    AcceptPatch("Accept-Patch"),
    AcceptRanges("Accept-Ranges"),
    Age("Age"),
    Allow("Allow"),
    CacheControl("Cache-Control"),
    Connection("Connection"),
    ContentDisposition("Content-Disposition"),
    ContentEncoding("Content-Encoding"),
    ContentLanguage("Content-Language"),
    ContentLength("Content-Length"),
    ContentLocation("Content-Location"),
    ContentMD5("Content-MD5"),
    ContentRange("Content-Range"),
    ContentType("Content-Type"),
    Date("Date"),
    ETag("ETag"),
    Expires("Expires"),
    LastModified("Last-Modified"),
    Link("Link"),
    Location("Location"),
    P3P("P3P"),
    Pragma("Pragma"),
    ProxyAuthenticate("Proxy-Authenticate"),
    PublicKeyPins("Public-Key-Pins"),
    Refresh("Refresh"),
    RetryAfter("Retry-After"),
    Server("Server"),
    SetCookie("Set-Cookie"),
    Status("Status"),
    StrictTransportSecurity("Strict-Transport-Security"),
    Trailer("Trailer"),
    TransferEncoding("Transfer-Encoding"),
    TSV("TSV"),
    Upgrade("Upgrade"),
    Vary("Vary"),
    Via("Via"),
    Warning("Warning"),
    WWWAuthenticate("WWW-Authenticate"),
    XFrameOptions("X-Frame-Options");

    private final String field;

    HeaderField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static HeaderField find(String f){
        for(HeaderField field : values())
            if(field.getField().equalsIgnoreCase(f)) return field;
        return null;
    }

    @Override
    public String toString() {
        return getField();
    }
};