package com.lamda.service.Lamda_Web_service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UserLambda implements RequestHandler<String, String>{
    @Override
    public String handleRequest(String s, Context context) {
        return "success";
    }
}