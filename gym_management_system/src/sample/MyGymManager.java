package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyGymManager implements GymManager{

    private List<DefaultMember> list=new ArrayList<>();

    @Override
    public void add(DefaultMember member) {
        list.add(member);

    }

    @Override
    public void delete(String mNo) {

    }

    @Override
    public void print() {

    }

    @Override
    public List<DefaultMember> sort() {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public String[] list() {
        try{
            File data = new File("data.txt");
            String list[]=new String[100];
            Scanner sc = new Scanner(data);
            String details = sc.nextLine();
            while (sc.hasNext()) {
                details = details + "\n" + sc.nextLine();
            }
            String s="\n";
            list=details.split(s);
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("no file");
        }

        return null;
    }
}
