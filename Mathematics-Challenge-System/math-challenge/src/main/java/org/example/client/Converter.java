


package org.example.client;
import org.json.JSONObject;

import java.util.Scanner;

public class Converter {
    User user;

    public Converter(User user) {
        this.user = user;
    }



    public String viewChallenges() {
        JSONObject obj = new JSONObject();
        obj.put("command", "viewChallenges");
        obj.put("isAuthenticated", this.user.isAuthenticated);
        obj.put("isStudent", this.user.isStudent);


        return obj.toString(4);
    }

    public String logout() {
        this.user.logout();
        return "Successfully logged out";
    }

    public String serialize(String command) {
        String[] tokens = command.split("\\s+");

        if (user.isStudent) {
            switch (tokens[0]) {
                case "logout":
                    return this.logout();

                case "viewChallenges":
                    return this.viewChallenges();

               case "attemptChallenge":


                default:
                    return "Invalid student command";
            }
        } else {
            switch (tokens[0]) {
                case "logout":
                    return this.logout();

                case "confirm":


                case "viewApplicants":

                default:
                   return "Invalid school representative command";
            }
        }

    }

    public static void main (String[] args) {
        Converter sample = new Converter(new User());
        sample.serialize("login nomwesigwakeith@gmail.com");
    }
}