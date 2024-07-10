package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("client connected....");
        try (
            Socket socket = new Socket("localhost",8000);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                Scanner scanner= new Scanner(System.in)){
                Menu();
                String command,response;
                do {
                    System.out.println("enter the command");
                    command= scanner.nextLine();
                    out.println(command);
                    if (command.equalsIgnoreCase("exit")){
                        System.out.println("process terminated");
                        break;
                    }
                    response = in.readLine();
                    System.out.println(response);
                }while (!command.equalsIgnoreCase("exit"));
                scanner.close();
                
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void Menu(){
        System.out.println("MENU:");
        String menu = """
               register username firstname lastname email dateOfBirth regNo imagePath\nparticipantlogin username password\nviewChallenges -to view available challenges\nattemptChallenge\nrepresentativeLogin userName password\nviewapplicants\nconfirmapplicants\ncheckStudent regNo
                """;
        System.out.println(menu);
    }
}
