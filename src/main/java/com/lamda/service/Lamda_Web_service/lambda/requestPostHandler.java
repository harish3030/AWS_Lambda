package com.lamda.service.Lamda_Web_service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lamda.service.Lamda_Web_service.controller.RequestController;
import com.lamda.service.Lamda_Web_service.model.Requests;
import org.apache.tomcat.util.json.JSONParser;

import java.io.DataInput;
import java.io.IOException;

public class requestPostHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        JSONObject input_json = new JSONPObject(input.getBody(), Requests.class);

        JSONParser parser = new JSONParser(input.getBody());

        RequestController controller = new RequestController();
        //Requests request= (Requests) input_json.getValue();

        try {
            Requests request = new ObjectMapper().readValue(input_json.toString(), Requests.class);
            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            response.setBody(controller.createRequest(new Long(1),request).getBody().toString());
            System.out.println("Response"+response);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
