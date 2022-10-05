package com.lamda.service.Lamda_Web_service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lamda.service.Lamda_Web_service.controller.DonationController;
import com.lamda.service.Lamda_Web_service.model.Donations;

public class donationGetHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        DonationController controller = new DonationController();

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setBody(controller.getAllDonationsByUserId(new Long(1)).getBody().toString());
        System.out.println(response);
        return response;

    }
}
