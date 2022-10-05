<?php

namespace App\Controllers;

class User extends BaseController
{
    public function index()
    {
       //User entry page
    }

    public function view()
    {
        helper(['curl']);
		$rest_api_base_url = 'localhost:8080';
		
		//GET - list of users
		$get_endpoint = '/users';
		
		$response = perform_http_request('GET', $rest_api_base_url . $get_endpoint);
		
		$data['users'] = $response;
		
        return view('display_users_result', $data);
    }

    
}