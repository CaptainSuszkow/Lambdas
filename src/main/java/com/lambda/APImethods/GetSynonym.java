package com.lambda.APImethods;

import com.amazonaws.services.lambda.runtime.Context;
import com.lambda.Model.APIRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class GetSynonym {

    private static String key = "dict.1.1.20200102T105247Z.c53378c59867a774.d28a87bfe672b972ff76799265c8b1e49fb91bd0";

    private static class Resp {
        LinkedList<String> synonyms;

        public LinkedList<String> getSynonyms() {
            return synonyms;
        }

        public void setSynonyms(LinkedList<String> synonyms) {
            this.synonyms = synonyms;
        }
    }

    public static Object handleRequest(APIRequest request, Context context) {

        try {
            Resp response = new Resp();
            response.synonyms = new LinkedList<>();
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
            JSONObject obj = new JSONObject(content.toString());
            JSONArray arr1 = obj.getJSONArray("def");
            if (arr1.length() > 0) {
                JSONArray arr2 = arr1.getJSONObject(0).getJSONArray("tr");
                for (Object item : arr2) {
                    JSONObject temp = (JSONObject) item;
                    if (((JSONObject) item).has("mean")) {
                        for (Object syn : temp.getJSONArray("mean")) {
                            response.synonyms.add(((JSONObject) syn).getString("text"));

                        }
                    }
                }
            }


            return response;
        } catch (Exception ex) {
            //return ex.getMessage();
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }
}
