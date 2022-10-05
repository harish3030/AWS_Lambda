package com.lamda.service.Lamda_Web_service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lamda.service.Lamda_Web_service.controller.RequestController;
import com.lamda.service.Lamda_Web_service.model.Requests;

public class requestPostHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        JSONPObject input_json = new JSONPObject(input.getBody(), Requests.class);

        RequestController controller = new RequestController();
        Requests request= (Requests) input_json.getValue();

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setBody(controller.createRequest(new Long(1),request).toString());

        System.out.println(request);
        System.out.println(response);
        return response;
    }
}
