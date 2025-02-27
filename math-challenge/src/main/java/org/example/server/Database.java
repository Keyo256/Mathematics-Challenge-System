
package org.example.server;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class Database {
    // database connection parameters
    String url = "jdbc:mysql://localhost:3306/mtchallenge";
    String username = "root";
    String password = "";
    Connection connection;
    Statement statement;

    public Database() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        this.statement = connection.createStatement();
    }

    public void create(String sqlCommand) throws SQLException {
        this.statement.execute(sqlCommand);
    }

    public ResultSet read(String sqlCommand) throws SQLException {
        return this.statement.executeQuery(sqlCommand);
    }

    public void update(String sqlCommand) throws SQLException {
        this.statement.execute(sqlCommand);
    }

    public void delete(String sqlCommand) throws SQLException {
        this.statement.execute(sqlCommand);
    }

    public void close() throws SQLException {
        if (this.statement != null) this.statement.close();
        if (this.connection != null) this.connection.close();
    }



    public ResultSet getChallenges() throws SQLException {
        String sql = "SELECT * FROM mtchallenge.challenge WHERE starting_date <= CURRENT_DATE AND closing_date >= CURRENT_DATE;";
        return this.statement.executeQuery(sql);
    }

    public ResultSet getChallengeQuestions(int challenge_id) throws SQLException {
        String sql = "SELECT qar.* FROM mtchallenge.question_answer_record qar JOIN mtchallenge.challenge_question_answer_record cqar ON qar.question_id = cqar.question_id WHERE cqar.challenge_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, challenge_id);
        return preparedStatement.executeQuery();
    }

    public int getAttemptScore(JSONArray attempt) throws SQLException {
        int score = 0;
        for (int i = 0; i < attempt.length(); i++) {
            JSONObject obj = attempt.getJSONObject(i);

            if (obj.get("answer").equals("-")) {
                score += 0;
                continue;
            }

            String sql = "SELECT score FROM question_answer_record WHERE question_id = " + obj.getInt("question_id") + " AND answer = " + obj.get("answer") + ";";
            ResultSet questionScore = this.statement.executeQuery(sql);

            if (questionScore.next()) {
                score += questionScore.getInt("score");
            } else {
                score -= 3;
            }

        }
        return score;
    }


}
