package com.robop.attendancerecord;

public class CustomListItem{

    private String subjectName; //教科名
    private int absentNum;  //欠席回数
    private int lateNum;    //遅刻回数

    CustomListItem(String subjectName, int absentNum, int lateNum){
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
