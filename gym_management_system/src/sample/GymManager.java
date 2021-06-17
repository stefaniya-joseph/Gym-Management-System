package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface GymManager {
    public void add(DefaultMember member);
    public void delete(String mNo);
    public void print();
    public void sort();
    public void save();
    public  String[] list();
}

package sample;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.List;
        import java.util.stream.Collectors;
        import java.util.stream.IntStream;

public class MyGymManager {

    public static void add(String mType, String mNumber, String mName, String mDate, String mSchool, int mAge) {
        try {
            File data = new File("data.txt");
            PrintWriter pWrite = new PrintWriter(new FileOutputStream(data, true));
            pWrite.append(mType + "," + mNumber + "," + mName + "," + mDate + "," + mSchool + "," + mAge + "\n");
            pWrite.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


    public static void delete(String mNo) {
        try {
            String[] list = GymManager.list();
            String[] each = new String[5];
            String j = ",";
            int count = 0;
            for (int i = 0; i < list.length; i++) {
                each = list[i].split(j);
                if (mNo.equalsIgnoreCase(each[1])) {
                    List<String> arrayNew = new ArrayList<String>();
                    Collections.addAll(arrayNew, list);
                    arrayNew.remove(i);
                    String[] list1 = arrayNew.toArray(new String[arrayNew.size()]);
                    count++;
                    String details = "";
                    for (int x = 0; x < list1.length; x++) {
                        details = details + list1[x] + "\n";
                    }
                    FileWriter myWriter = new FileWriter("data.txt");
                    myWriter.write(details);
                    myWriter.close();
                }
            }
            if (count == 0) {
                System.out.println("Couldn't find the membership number");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void printList() {
        String[] list = GymManager.list();
        String[] each = new String[5];
        String j = ",";
        for (int i = 0; i < list.length; i++) {
            each = list[i].split(j);
            System.out.print("Membership type: " + each[0] + "  ");
            System.out.print("Membership Number: " + each[1] + "  ");
            System.out.print("Name of the member: " + each[2] + "  ");
            System.out.println("Membership Date: " + each[3] + "  ");
        }
    }


    public static void sortByName() {
        String[] list = GymManager.list();
        String[][] each = new String[5][];
        String[] each1 = new String[5];
        String j = ",";
        for (int i = 0; i < ( list.length - 1 ); i++) {
            list = list[i].split(j);
            String detail = each[i][1];
            System.out.println(detail);
        }
    }

    public static void save() {
        System.out.println("All the details of the members are successfully stored");
    }

    public static void getMemberListinTable() {
        Stage window = new Stage();
        window.setTitle("Adding a new member");
        Label lb1 = new Label("Whom do you want to add?");
        lb1.setLayoutX(300);
        lb1.setLayoutY(20);
        String[] details = GymManager.list();
        String[] each = new String[7];
        String j = ",";
        //Creating the table.
        TableView<DataTable> table = new TableView<DataTable>();
        ObservableList<DataTable> data = FXCollections.observableArrayList();
        //Creating Columns.
        TableColumn typeMemberCol = new TableColumn("Type of Member");
        typeMemberCol.setCellValueFactory(new PropertyValueFactory<>("typeMember"));
        TableColumn membershipNoCol = new TableColumn("Membership No");
        membershipNoCol.setCellValueFactory(new PropertyValueFactory("membershipNo"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn schoolNameCol = new TableColumn("School Name");
        schoolNameCol.setCellValueFactory(new PropertyValueFactory("schoolName"));
        TableColumn memberAgeCol = new TableColumn("Age");
        memberAgeCol.setCellValueFactory(new PropertyValueFactory("memberAge"));
        TableColumn dateCol = new TableColumn("Start Membership Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));
        ObservableList<String> list = FXCollections.observableArrayList();
        table.setItems(data);
        table.getColumns().addAll(typeMemberCol, membershipNoCol, nameCol, schoolNameCol, memberAgeCol, dateCol);

        //Adding data from the file "data.txt" to the table.
        for (int i = 0; i < details.length; i++) {
            each = details[i].split(j);
            DataTable addRecord = new DataTable(each[0], each[1], each[2], each[3], each[4], each[5]);
            table.getItems().add(addRecord);
        }
        //Setting the size of the table
        table.setMaxSize(650, 300);
        VBox vbox = new VBox();
        vbox.setSpacing(5);

        vbox.getChildren().addAll(table);
        //Setting the scene
        Scene scene = new Scene(vbox, 700, 400);
        window.setTitle("List of Members.");
        window.setScene(scene);
        window.show();
    }


    public static void searchByMembershipNo(Pane root) {
        Label lb1=new Label("Enter the Membership number: ");
        lb1.setLayoutX(180);
        lb1.setLayoutY(110);
        Button show=new Button("Show Results");
        show.setLayoutY(140);
        show.setLayoutY(150);
        Label result=new Label();
        result.setLayoutX(150);
        result.setLayoutY(170);
        TextField answer=new TextField();
        answer.setLayoutX(120);
        answer.setLayoutY(110);
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] list = GymManager.list();
                String[] each = new String[5];
                String j = ",";
                int count = 0;
                String mNo=answer.getText();
                for (int i = 0; i < list.length; i++) {
                    each = list[i].split(j);
                    if (mNo.equalsIgnoreCase(each[1])) {
                        count++;
                        String Text="Membership Number: "+each[1]+"\nMember Name: "+each[2]
                                +"\nMembership date: "+each[3];

                        if("default".equalsIgnoreCase(each[0])){
                            result.setText(Text);
                        }else if("student".equalsIgnoreCase(each[0])){
                            result.setText(Text+"Member School: "+each[4]);
                        }else{
                            result.setText(Text+"Member Age: "+each[5]);
                        }

                    }
                }
                if(count==0){
                    result.setText("Sorry couldn't find");
                }
            }
        });
        root.getChildren().addAll(lb1,result,show,answer);
    }

    public static void searchByMemberName(Pane root) {
        Label lb1=new Label("Enter the name of the member: ");
        lb1.setLayoutX(180);
        lb1.setLayoutY(110);
        Button show=new Button("Show Results");
        show.setLayoutY(140);
        show.setLayoutY(150);
        Label result=new Label();
        result.setLayoutX(150);
        result.setLayoutY(170);
        TextField answer=new TextField();
        answer.setLayoutX(120);
        answer.setLayoutY(110);
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] list = GymManager.list();
                String[] each = new String[5];
                String j = ",";
                int count = 0;
                String mName=answer.getText();
                for (int i = 0; i < list.length; i++) {
                    each = list[i].split(j);
                    if (mName.equalsIgnoreCase(each[2])) {
                        count++;
                        String Text="Membership Number: "+each[1]+"\nMember Name: "+each[2]
                                +"\nMembership date: "+each[3];

                        if("default".equalsIgnoreCase(each[0])){
                            result.setText(Text);
                        }else if("student".equalsIgnoreCase(each[0])){
                            result.setText(Text+"Member School: "+each[4]);
                        }else{
                            result.setText(Text+"Member Age: "+each[5]);
                        }

                    }
                }
                if(count==0){
                    result.setText("Sorry couldn't find");
                }
            }
        });
        root.getChildren().addAll(lb1,result,show,answer);
    }
}
