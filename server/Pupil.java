package server;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Pupil {
    private String name;
    private String userName;
    private String email;
    private String regNo;
    private String dateOfBirth;
    private String imagePath;

    // Constructor
    public Pupil(String userName, String name, String email, String dateOfBirth, String regNo, String imagePath) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.regNo = regNo;
        this.imagePath = imagePath;
    }

    // Getters and setters

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getUsername() {
        return userName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }
    public String toString() {
        return "Pupil{" +
                "name='" + name + '\'' +
                "username='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", regNo='" + regNo + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    // Register participant
    public static void register(Pupil pupil) {
        String name = pupil.getName();
        String username = pupil.getUsername();
        String email = pupil.getEmail();
        String dateOfBirth = pupil.getDateOfBirth();
        String regNo = pupil.getRegNo();

        String sql = "INSERT INTO participant(username, name, email, dateOfBirth, regNo) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Database.Connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, dateOfBirth);
            statement.setString(5, regNo);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while registering participant: " + e.getMessage());
        }
    }
    // login method
    public static void participantLogin(String[] request, PrintWriter out) {
        if (request.length != 3) {
            out.println("Missing parameters");
            return;
        }
        String username = request[1];
        String password = request[2];
        switch (request[1]) {
            case "participant":
                if (Database.checkParticipantLogin(username, password)) {
                    out.println("Login successful");
                } else {
                    out.println("Invalid credentials");
                }
                break;
            default:
                out.println("Invalid login type");
                break;
        }
    }
    // View challenges
    public static String viewChallenges(PrintWriter printWriter) {
        String chal = null;
        String challengeID;
        String challengeName;

        try (Connection connection = Database.Connect();
             Statement stmt = connection.createStatement()) {

            String sql = "SELECT ChallengeID, ChallengeName FROM Challenge WHERE Status = 'Valid'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                challengeID = rs.getString("ChallengeID");
                challengeName = rs.getString("ChallengeName");
                chal = challengeID + "-" + challengeName;
                printWriter.println(chal);
            }
        } catch (SQLException e) {
            System.out.println("Error while viewing challenges: " + e.getMessage());
        }

        return chal;
    }

     // Check for registration number
    public boolean checkRegNo(Pupil pupil) {
        String regNo = pupil.getRegNo();
        ArrayList<String> regNos = new ArrayList<>();

        try (Connection connection = Database.Connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT regNo FROM School";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                regNos.add(resultSet.getString("regNo"));
            }

        } catch (SQLException e) {
            System.out.println("Error while checking registration number: " + e.getMessage());
        }

        return regNos.contains(regNo);
    }

    // Create a file
    public void createFile() {
        try {
            File file = new File("pupils.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    // Add pupil details to a file
    public void addToFile(Pupil pupil) {
        try (FileWriter fileWriter = new FileWriter("pupils.txt.", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(pupil.getName() + "," + pupil.getUsername() + "," +
                                 pupil.getEmail() + "," + pupil.getDateOfBirth() + "," +
                                 pupil.getRegNo() + "," + pupil.getImagePath() + "\n");

            System.out.println("Details have been added to a file.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding to file: " + e.getMessage());
        }
    }
}
