package com.lambda.APImethods;

import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Model.APIRequest;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetTranslate {

    private static String key = "trnsl.1.1.20200102T105228Z.1c400edfb7c7ec87.a4db8950d6b70a4358f9146c436c957b9cded3f4";

    public static Object handleRequest(APIRequest request, Context context) {

        try {
            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate" +
                    "?key=" + key +
                    "&text=" + request.getText() +
                    "&lang=" + request.getLang());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } catch (Exception ex) {
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }
}
