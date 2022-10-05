<?php

namespace App\Controllers;

class Home extends BaseController {
	
    public function index() {

        return view('home_page');       
    }

    public function post_request() {

        helper(['curl']);
		
		$rest_api_base_url = 'localhost:8080';

        //POST - create new user
		$post_endpoint = '/users/1/requests';
		
		$request_data = json_encode(); //
		
		$response = perform_http_request('POST', $rest_api_base_url . $post_endpoint, $request_data);
		
		$data['new_user'] = $response;

    }

}
