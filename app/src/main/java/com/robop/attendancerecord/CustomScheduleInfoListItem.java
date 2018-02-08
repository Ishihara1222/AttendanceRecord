package com.robop.attendancerecord;


public class CustomScheduleInfoListItem {

    private String subjectName;
    private int absentNum;
    private int lateNum;

    public CustomScheduleInfoListItem(String subjectName, int absentNum, int lateNum){
        this.subjectName = subjectName;
        this.absentNum = absentNum;
        this.lateNum = lateNum;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getAbsentNum() {
        return absentNum;
    }

    public int getLateNum() {
        return lateNum;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setAbsentNum(int absentNum) {
        this.absentNum = absentNum;
    }

    public void setLateNum(int lateNum) {
        this.lateNum = lateNum;
    }
}
