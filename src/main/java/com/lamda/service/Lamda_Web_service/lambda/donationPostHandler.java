package com.lamda.service.Lamda_Web_service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lamda.service.Lamda_Web_service.controller.DonationController;
import com.lamda.service.Lamda_Web_service.controller.RequestController;
import com.lamda.service.Lamda_Web_service.model.Donations;
import com.lamda.service.Lamda_Web_service.model.Requests;

public class donationPostHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        JSONPObject input_json = new JSONPObject(input.getBody(), Donations.class);
        DonationController controller = new DonationController();
        Donations donations= (Donations) input_json.getValue();

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setBody(controller.createDonation(new Long(1),donations).toString());
        System.out.println(donations);
        System.out.println(response);
        return response;
    }
}
