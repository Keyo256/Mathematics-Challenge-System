<?php
namespace App\Http\Controllers;

use App\Models\School;
use Illuminate\Http\Request;

class SchoolController extends Controller
{
    public function index()
    {
        $schools = School::all();
        return view('admin.manage-schools', compact('schools'));
    }

    public function create()
    {
        return view('admin.manage-schools-create');
    }

    public function store(Request $request)
    {
        $request->validate([
            'name' => 'required',
            'district' => 'required',
            'regNo' => 'required|unique:schools',
            'representative_name' => 'required',
            'email' => 'required|email|unique:schools',
        ]);

        School::create($request->all());

        return redirect()->route('schools.index')->with('success', 'School added successfully.');
    }

    public function edit(School $school)
    {
        return view('admin.manage-schools-edit', compact('school'));
    }

    public function update(Request $request, School $school)
    {
        $request->validate([
            'name' => 'required',
            'district' => 'required',
            'regNo' => 'required|unique:schools,regNo,' . $school->id,
            'representative_name' => 'required',
            'email' => 'required|email|unique:schools,email,' . $school->id,
        ]);

        $school->update($request->all());

        return redirect()->route('schools.index')->with('success', 'School updated successfully.');
    }

    public function destroy(School $school)
    {
        $school->delete();

        return redirect()->route('schools.index')->with('success', 'School deleted successfully.');
    }
}
