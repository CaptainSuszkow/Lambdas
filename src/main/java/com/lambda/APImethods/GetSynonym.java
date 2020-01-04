package com.lambda.APImethods;

import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Model.APIRequest;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetSynonym {

    private static String key = "dict.1.1.20200102T105247Z.c53378c59867a774.d28a87bfe672b972ff76799265c8b1e49fb91bd0";

    public static Object handleRequest(APIRequest request, Context context) {

        try {
            URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/lookup" +
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
