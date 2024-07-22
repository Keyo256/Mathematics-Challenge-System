package org.example.client;

import org.json.JSONArray;
import org.json.JSONObject;

public class ClientHandler {
    User user;


    public ClientHandler(User user) {
        this.user = user;
    }


    private User viewChallenges(JSONObject response) {
        // logic to interpret server response in attempt to view challenges
        JSONArray challenges = new JSONArray(response.getString("challenges"));

        if (challenges.isEmpty()) {
            this.user.output = " No open challenges yet";
            return this.user;
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nCHALLENGES \n\n");
        for (int i = 0; i < challenges.length(); i++) {
            JSONObject challenge = new JSONObject(((JSONObject) challenges.get(i)).toString(4));
            stringBuilder.append("challenge id: " + challenge.get("id") + "\nchallenge name: " + challenge.getString("name") + "\ndifficulty: " + challenge.getString("difficulty") + "\nclosing date: " + challenge.getString("closing_date") + "\t\tduration: " + challenge.getInt("time_allocation") + "\n\n\n");
        }

        stringBuilder.append("Attempt a particular challenge using the command:\n - attemptChallenge [challenge_id]\n\n");

        this.user.output = stringBuilder.toString();

        return this.user;
    }


    public User exec(String responseData) {
        if (responseData==null|| responseData.isEmpty()){
            throw  new IllegalArgumentException("response data is null");
        }
        JSONObject response = new JSONObject(responseData);
        switch (response.get("command").toString()) {
            case "login":


            case "register":


            case "attemptChallenge":

            case "viewChallenges":
                return this.viewChallenges(response);

            case "confirm":


            case "viewApplicants":


            default:
                throw new IllegalStateException("Invalid response");
        }
    }
}

