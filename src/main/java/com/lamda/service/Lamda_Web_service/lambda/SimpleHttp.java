package com.lamda.service.Lamda_Web_service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class SimpleHttp implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        System.out.println(input.getBody());

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setBody("{\"name\" : \"duke\"}");
        response.setStatusCode(200);

        Map<String, String> headers = new HashMap();
        headers.put("X-Custom-Header", "Hello World from Serverless!");
        response.setHeaders(headers);

        return response;
    }
}
