package com.lamda.service.Lamda_Web_service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lamda.service.Lamda_Web_service.LamdaWebServiceApplication;
import com.lamda.service.Lamda_Web_service.controller.DonationController;
import com.lamda.service.Lamda_Web_service.controller.RequestController;
import com.lamda.service.Lamda_Web_service.model.Donations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class donationGetHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>, ApplicationContextAware {
    @Autowired
    DonationController controller;

    private static ApplicationContext springContext = SpringApplication.run(LamdaWebServiceApplication.class);

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        this.controller = springContext.getBean(DonationController.class);
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setBody(controller.getAllDonationsByUserId(new Long(1)).getBody().toString());
        return response;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.springContext = applicationContext;
    }
}

