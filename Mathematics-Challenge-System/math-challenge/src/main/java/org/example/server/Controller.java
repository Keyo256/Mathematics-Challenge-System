
package org.example.server;

import org.json.*;

        import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    JSONObject obj;

    public Controller(JSONObject obj) {
        this.obj = obj;
    }



    private JSONObject viewChallenges(JSONObject obj) throws SQLException, ClassNotFoundException {
        JSONObject clientResponse = new JSONObject();

        Database database = new Database();
        ResultSet availableChallenges = database.getChallenges();
        JSONArray challenges = new JSONArray();

        while (availableChallenges.next()) {
            JSONObject challenge = new JSONObject();
            challenge.put("id", availableChallenges.getInt("challenge_id"));
            challenge.put("name", availableChallenges.getString("challenge_name"));
            challenge.put("difficulty", availableChallenges.getString("difficulty"));
            challenge.put("time_allocation", availableChallenges.getInt("time_allocation"));
            challenge.put("starting_date", availableChallenges.getDate("starting_date"));
            challenge.put("closing_date", availableChallenges.getDate("closing_date"));

            challenges.put(challenge);
        }

        clientResponse.put("command", "viewChallenges");
        clientResponse.put("challenges", challenges.toString());

        return clientResponse;
    }



    public JSONObject run() throws IOException, SQLException, ClassNotFoundException, MessagingException {
        switch (this.obj.get("command").toString()) {
            case "login":

            case "register":


            case "viewChallenges":
                return this.viewChallenges(this.obj);

            case "attemptChallenge":


            case "confirm":


            case "viewApplicants":


            case "attempt":


            default:
                // command unresolved
                JSONObject outputObj = new JSONObject();
                outputObj.put("command", "exception");
                outputObj.put("reason", "Invalid command");

                return outputObj;
        }
    }
}
