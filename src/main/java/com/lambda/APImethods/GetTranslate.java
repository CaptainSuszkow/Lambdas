package com.lambda.APImethods;

import com.amazonaws.services.lambda.runtime.Context;
import com.github.vbauer.yta.model.Direction;
import com.github.vbauer.yta.model.Language;
import com.github.vbauer.yta.model.Translation;
import com.github.vbauer.yta.service.YTranslateApiImpl;
import com.lambda.Model.APIRequest;

import javax.ws.rs.core.Response;

public class GetTranslate {

    private static String key = "trnsl.1.1.20200102T105228Z.1c400edfb7c7ec87.a4db8950d6b70a4358f9146c436c957b9cded3f4";

    public static Object handleRequest(APIRequest request, Context context) {

        try {
            YTranslateApiImpl api = new YTranslateApiImpl(key);
            Direction dir = Direction.of(Language.PL, Language.EN);
            if (request.getLang().equals("en-pl")) {
                dir = Direction.of(Language.EN, Language.PL);
            }
            Translation translation = api.translationApi().translate(request.getText(), dir);
            return translation.text();
        } catch (Exception ex) {
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }
}
