package sample;

public class StudentMember extends DefaultMember{
    private String mSchool;

    public StudentMember(String mNumber, String mName, String mDate,String mSchool) {
        super(mNumber, mName, mDate);
        this.mSchool=mSchool;
    }

    public String getmSchool() {
        return mSchool;
    }

    public void setmSchool(String mSchool) {
        this.mSchool = mSchool;
    }
}