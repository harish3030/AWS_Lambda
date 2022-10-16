package com.lamda.service.Lamda_Web_service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lamda.service.Lamda_Web_service.LamdaWebServiceApplication;
import com.lamda.service.Lamda_Web_service.controller.RequestController;
import com.lamda.service.Lamda_Web_service.model.Requests;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.util.Map;

public class requestPostHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>, ApplicationContextAware {

    @Autowired
    RequestController controller;

    private static ApplicationContext springContext = SpringApplication.run(LamdaWebServiceApplication.class);

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context){
        try {
            this.controller = springContext.getBean(RequestController.class);
            Map<String, String> pathParameters = input.getPathParameters();
            String id = pathParameters.get("id");
            Long user_id=Long.parseLong(id);
            Requests request = new ObjectMapper().readValue(input.getBody(), Requests.class);
            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            response.setBody(controller.createRequest(user_id,request).getBody().toString());
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.springContext = applicationContext;
    }
}
