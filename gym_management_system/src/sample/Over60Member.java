package sample;

import java.util.Scanner;

public class Over60Member extends DefaultMember{
    private int mAge;

    public Over60Member(String mNumber, String mName, String mDate,int mAge) {
        super(mNumber, mName, mDate);
        this.setmAge(mAge);
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        while(mAge<60){
            System.out.println("Please enter the correct age.");

        }
        this.mAge = mAge;


    }
}
