package sample;

public class DefaultMember implements Comparable<DefaultMember>{
    private String mNumber;
    private String mName;
    private String mDate;

    public DefaultMember(String mNumber,String mName,String mDate){
        super();
        this.mNumber=mNumber;
        this.mName=mName;
        this.mDate=mDate;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    @Override
    public int compareTo(DefaultMember o) {
        return this.mName.compareTo(o.getmName());
    }
}
