<?php

namespace App\Controllers;

class Bank extends BaseController
{
    public function index()
    {
       //Bank entry page
    }

    public function view()
    {
        helper(['curl']);
		$rest_api_base_url = 'localhost:8080';
		
		//GET - list of users
		$get_endpoint = '/banks';
		
		$response = perform_http_request('GET', $rest_api_base_url . $get_endpoint);
		
		$data['banks'] = $response;
		
        return view('display_banks_result', $data);
    }

    
}