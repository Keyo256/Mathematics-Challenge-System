<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Challenge extends Model
{
    use HasFactory;

    protected $fillable = [
        'challenge_name',
        'start_date',
        'end_date',
        'duration',
        // Add more fields as needed
    ];

    // Optional: Define relationships with other models
    // For example, if a Challenge has many Questions:
    public function questions()
    {
        return $this->hasMany(Question::class);
    }
}
