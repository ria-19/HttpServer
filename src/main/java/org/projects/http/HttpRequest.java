package org.projects.http;

public class HttpRequest extends HttpMessage{
    private HttpMethod method;
    private String requestTarget;
    private String originalHttpVersion; // literal from the request
    private HttpVersion bestCompatibleVersion;
    HttpRequest() {

    }

    public String getOriginalHttpVersion() {
        return originalHttpVersion;
    }

    void setOriginalHttpVersion(String originalHttpVersion) throws BadHttpVersionException, HttpParsingException {
        this.originalHttpVersion = originalHttpVersion;
        this.bestCompatibleVersion = HttpVersion.getBestCompatibleVersion(originalHttpVersion);
        if (this.bestCompatibleVersion == null){
            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED);
        }
    }

    public HttpVersion getBestCompatibleVersion() {
        return bestCompatibleVersion;
    }
    public HttpMethod getMethod() {
        return method;
    }

     void setMethod(String methodName) throws HttpParsingException {
         for(HttpMethod method: HttpMethod.values()){
             if(methodName.equals(method.name())){
                 this.method = method;
                 return;
             }
         }
         throw new HttpParsingException(
                 HttpStatusCode.SERVER_ERROR_501_METHOD_NOT_IMPLEMENTED
         );
    }

    public String getRequestTarget() {
        return requestTarget;
    }

     void setRequestTarget(String requestTarget) throws HttpParsingException {
         if (requestTarget == null || requestTarget.length() == 0){
             throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
         }
        this.requestTarget = requestTarget;
    }
}
