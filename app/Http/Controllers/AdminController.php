<?php
// app/Http/Controllers/AdminController.php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;
use App\Models\Question;
use App\Models\Answer;

class AdminController extends Controller
{
    public function showUploadForm()
    {
        return view('admin.upload-questions');
    }

    public function uploadQuestions(Request $request)
    {
        // Validate the uploaded files
        $request->validate([
            'questionsFile' => 'required|file|mimes:xlsx',
            'answersFile' => 'required|file|mimes:xlsx',
        ]);

        // Handle file uploads
        $questionsFile = $request->file('questionsFile');
        $answersFile = $request->file('answersFile');

        // Store files in storage/app/uploads
        $questionsPath = $questionsFile->storeAs('uploads', 'questions.xlsx');
        $answersPath = $answersFile->storeAs('uploads', 'answers.xlsx');

        // Process the files (for example, read and save to the database)
        $this->processQuestions($questionsPath);
        $this->processAnswers($answersPath);

        return redirect()->route('admin.upload-questions-form')->with('success', 'Questions and answers uploaded successfully.');
    }

    private function processQuestions($path)
    {
        // Implement your logic to process the questions file
        // For example, reading the file and saving data to the database
    }

    private function processAnswers($path)
    {
        // Implement your logic to process the answers file
        // For example, reading the file and saving data to the database
    }
}
