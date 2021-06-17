package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println("~~~~~~~~~~~~~~Gym Management System~~~~~~~~~~~~~~~");


        System.out.println("Select your function from bellow.\n\n\t1. Add a new Member: Enter 1\n\t" +
                "2. Delete a member: Enter 2\n\t3. Print the list: Enter 3\n\t" +
                "4. Sort in assending order by name: Enter 4\n\t5.To save all the details: Enter 5" +
                "\n\t6. Search a Member: Enter 6" +
                "\n\t7. To view the list in a table: Enter 7\n\t8. To end the program: Enter 8");
        Scanner myObj = new Scanner(System.in);
        System.out.println("\nEnter Your selection(1,2,3,4 or 5): ");
        int select = myObj.nextInt();

        //The function for adding a new member.
        if (select == 1) {
            insertMember();
        }
    }

    private static void insertMember() {
        Scanner input = new Scanner(System.in);

        MyGymManager addMember=new MyGymManager();

            System.out.println("Enter the membership No : ");
            String membershipNo = input.next();
            System.out.println("Enter the name : ");
            String name = input.next();
            System.out.println("Enter the date in the following format DD/MM/YYYY : ");
            String membershipStartDate = input.next();
            System.out.println("Enter the type of membership (D - Default Member/ S - Student Member / O - Over60 Member) : ");
            String type = input.next();
            DefaultMember member = null;

            switch(type) {
                case "D":
                case "d":
                    member = new DefaultMember(membershipNo, name, membershipStartDate);
                    break;
                case "S":
                case "s":
                    String schoolName = input.next();
                    member = new StudentMember(membershipNo, name, membershipStartDate, schoolName);
                    break;
                case "O":
                case "o":
                    int age = input.nextInt();
                    member = new Over60Member(membershipNo, name, membershipStartDate, age);
                    break;
                default:
                    System.out.println("Invalid Input");

            }

            addMember.add(member);
            try{
                File data = new File("data.txt");
                PrintWriter pWrite = new PrintWriter(new FileOutputStream(data, true));
                pWrite.append(member+ "\n");
                pWrite.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
