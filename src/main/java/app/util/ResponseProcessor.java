package app.util;

import spark.Response;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class ResponseProcessor {
    public static void processResult(Response response, Boolean result) {
        if (result)
            response.status(HTTP_OK);
        else {
            response.status(HTTP_BAD_REQUEST);
            //response.body(result);
        }
    }
}
