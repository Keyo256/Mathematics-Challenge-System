package server;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started..\nWaiting for connection");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected");

    
                executorService.submit(() -> {
                    try {
                        handleClientCommand(socket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handleClientCommand(Socket socket) throws IOException {
        try (socket;
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String clientCommand;
            while ((clientCommand = bufferedReader.readLine()) != null) {
                System.out.println("Client: " + clientCommand);
                String[] command = clientCommand.split( " ");
                int length = command.length;
                switch (command[0]) {
                    case "register": {
                        if (length == 8) {
                            String username = command[1];
                            String name = command[2] + " " + command[3]; 
                            String email = command[4];
                            String dateOfBirth = command[5];
                            String regNo = command[6];
                            String imagePath = command[7];
                            Pupil pupil = new Pupil(username, name, email, dateOfBirth, regNo, imagePath);
                            // Check validity of regNo
                            boolean regNoValid = pupil.checkRegNo(pupil);
                            if (!regNoValid) {
                                printWriter.println("invalid regNo");
                            } else {
                                pupil.createFile();
                                pupil.addToFile(pupil);
                                printWriter.println("Added to file, waiting for verification email");
                                return;
                            }
                        } else {
                            System.out.println("Insufficient details");
                            printWriter.println("insufficient details");
                        }
                    
                    }
                case "participantlogin":
                if (command.length == 3) {
                    String userName = command[1];
                    String password = command[2];
                    boolean loggedIn = Database.checkParticipantLogin(userName, password);
                    if (loggedIn) {
                        printWriter.println("login successful");
                        
                    } else {
                        printWriter.println("login failed");
                    }
                } else {
                    printWriter.println("invalid command");
                }
                break;

                    case "viewChallenge":
                        // 
            
                    case "attemptChallenge":
                        // 

                    
                    case "representativeLogin":
                        if (command.length == 3) {
                            String userName = command[1];
                            String password = command[2];
                            boolean loggedIn = Database.checkRepresentativeLogin(userName, password);
                            if (loggedIn) {
                                printWriter.println("login successful");
                                // Call representative methods
                            } else {
                                printWriter.println("login failed");
                            }
                        } else {
                            printWriter.println("invalid command");
                        }
                         
                    case "viewapplicants":

                              String filePath = "pupils.txt"; // Replace with your actual file path
        List<Pupil> pupils = SchoolRep.viewPupilsFromFile(filePath);

        // Display applicants 
        for (Pupil pupil : pupils) {
            System.out.println(pupil);
            printWriter.println();
        }
        
        SchoolRep.confirmApplicants("23um");
         case "confirmapplicants":
                        // Implement confirmApplicants logic
String filepath ="pupils.txt";
                    SchoolRep.confirmApplicant(filepath);
                    printWriter.println();
                   /*  case "checkStudent":
                        if (command.length == 2) {
                            String[][] response = getPupil(command[1]);
                            printWriter.println(Arrays.deepToString(response));
                        } else {
                            printWriter.println("missing");
                        }*/
                     
                    case "exit":
                        // Implement exit logic if needed
                        break;
                    default:
                        String response = "invalid command";
                        printWriter.println(response);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[][] getPupil(String pupilId) {
        String sql = "SELECT * FROM pupil WHERE pupil_id = " + pupilId; // Example SQL query
        String[][] result = new String[10][3]; // Example result structure

        try (Connection connection = Database.Connect();
             Statement statement = connection.createStatement();
             ResultSet pupils = statement.executeQuery(sql)) {

            int i = 0;
            while (pupils.next() && i < 10) { // Limiting to 10 rows for example
                result[i][0] = pupils.getString("pupil_id");
                result[i][1] = pupils.getString("username");
                result[i][2] = pupils.getString("email");
                i++;
            }
            System.out.println(Arrays.deepToString(result));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
            
