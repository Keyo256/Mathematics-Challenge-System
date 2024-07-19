<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;

class ParticipantController extends Controller
{
    public function showLoginForm()
    {
        return view('auth.participant-login'); // Make sure this view exists
    }
}
