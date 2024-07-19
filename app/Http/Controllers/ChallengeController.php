<?php
// app/Http/Controllers/ChallengeController.php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Challenge;

class ChallengeController extends Controller
{
    public function index()
    {
        $challenges = Challenge::all();
        return view('admin.manage-challenges', compact('challenges'));
    }

    public function create()
    {
        // Show form to create a new challenge
        return view('admin.create-challenge');
    }

    public function store(Request $request)
    {
        // Validate and store new challenge
        $validatedData = $request->validate([
            'challenge_name' => 'required|string|max:255',
            'start_date' => 'required|date',
            'end_date' => 'required|date',
            'duration' => 'required|integer',
            // Add more validation rules as needed
        ]);

        Challenge::create($validatedData);

        return redirect()->route('challenges.index')
                         ->with('success', 'Challenge created successfully');
    }

    public function edit($id)
    {
        $challenge = Challenge::findOrFail($id);
        return view('admin.edit-challenge', compact('challenge'));
    }

    public function update(Request $request, $id)
    {
        $validatedData = $request->validate([
            'challenge_name' => 'required|string|max:255',
            'start_date' => 'required|date',
            'end_date' => 'required|date',
            'duration' => 'required|integer',
            // Add more validation rules as needed
        ]);

        $challenge = Challenge::findOrFail($id);
        $challenge->update($validatedData);

        return redirect()->route('challenges.index')
                         ->with('success', 'Challenge updated successfully');
    }

    public function destroy($id)
    {
        $challenge = Challenge::findOrFail($id);
        $challenge->delete();

        return redirect()->route('challenges.index')
                         ->with('success', 'Challenge deleted successfully');
    }
}
