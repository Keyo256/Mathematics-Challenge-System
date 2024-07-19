<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class UploadQuestionsController extends Controller
{
    public function showUploadForm()
    {
        return view('admin.upload-questions');
    }

    public function uploadQuestions(Request $request)
    {
        // Validate the request
        $request->validate([
            'questionsFile' => 'required|file|mimes:xlsx',
            'answersFile' => 'required|file|mimes:xlsx',
        ]);

        // Handle file upload
        $questionsFile = $request->file('questionsFile');
        $answersFile = $request->file('answersFile');

        // Process the files (e.g., store them, read content, etc.)

        // Redirect or return with a success message
        return redirect()->route('showUploadForm')->with('success', 'Files uploaded successfully.');
    }
}
