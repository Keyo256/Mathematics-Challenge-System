package server;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SchoolRep {
    private String name;
    private String userName;
    private String email;
    private String password;
    private String regNo;

    public SchoolRep(String name, String userName, String email, String password, String regNo) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.regNo = regNo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String filePath ="pupils.txt";
    // Login method
    public static void representativeLogin(String[] request, PrintWriter out) {
        if (request.length != 3) {
            out.println("Missing parameters");
            return;
        }
        String username = request[1];
        String password = request[2];
        switch (request[1]) {
            case "representative":
                if (Database.checkRepresentativeLogin(username, password)) {
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
    
    // Method to view applicants from file
    public static List<Pupil> viewPupilsFromFile(String filePath) {
      
        List<Pupil> pupils = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Assuming data is comma-separated
                if (data.length == 6) { 
                    String name = data[0].trim();
                    String userName = data[1].trim();
                    String email = data[2].trim();
                    String dateOfBirth = data[3].trim();
                    String regNo = data[4].trim();
                    String imagePath = data[5].trim();

                    Pupil pupil = new Pupil(name, userName,email, dateOfBirth, regNo, imagePath);
                    pupils.add(pupil);
                } else {
                    System.out.println("Invalid data format for line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pupils;
    }
 // Confirm applicants
 public static void confirmApplicant(String filePath) throws IOException{
    
    Scanner scanner = new Scanner(System.in);

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        boolean confirmedAll = false;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");

            if (parts.length == 7) { // Assuming at least Name, Age, and Course fields
                String username = parts[0].trim();
                String firstname = parts[1].trim();
                String lastname = parts[2].trim();
                String email = parts[3].trim();
                String dateofBirth = parts[4].trim();
                String regNo = parts[5].trim();
                 String imagePath = parts[6].trim();

                // Display applicant information
                System.out.println(" userName: " + username);
                System.out.println("firstname: " + firstname);
                System.out.println("lastname: " + lastname);
                System.out.println("email: " + email);
                System.out.println(": " + dateofBirth);
                System.out.println("reg no: " + regNo);
                System.out.println("imagepath: " + imagePath);

                
                // Ask for confirmation
                System.out.print("Do you confirm this applicant? (yes/no): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();

                if (confirmation.equals("yes")) {
    
                    System.out.println("Applicant confirmed.");
                } else {
            
                    System.out.println("Applicant not confirmed.");
                }

                System.out.println(); // Empty line for readability
            }
        }

        confirmedAll = true; // Set true if all applicants are confirmed
        if (confirmedAll) {
            System.out.println("All applicants confirmed. Process completed.");
        }

    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
        System.err.println("Error reading file: " + e.getMessage());
    } finally {
        scanner.close(); // Close scanner
    }
}


  public static void confirmApplicants(String regNo) {
        try (Connection connection = Database.Connect()) {
            String sql = "UPDATE applicants SET confirmed = true WHERE regNo = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, regNo);
                preparedStatement.executeUpdate();
                System.out.println("Applicant with regNo " + regNo + " confirmed successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error confirming applicant: " + e.getMessage());
        }
    }
}
