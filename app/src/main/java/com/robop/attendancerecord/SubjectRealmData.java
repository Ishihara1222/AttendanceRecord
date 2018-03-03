package com.robop.attendancerecord;

import io.realm.RealmObject;

public class SubjectRealmData extends RealmObject {

    private Integer subjectId;  //教科ID
    private String subjectName; //教科名
    private int attendNum;  //出席回数
    private int absentNum;  //欠席回数
    private int lateNum;    //遅刻回数
    private Integer listId;    //月曜1限 : 0 〜 土曜5限 : 29としてListViewの行の数と同期
    private Integer dayOfWeekId;    //月曜 : 0 〜 土曜 : 5  として曜日の数と同期

    public Integer getSubjectId() {
        return subjectId;
    }

    public Integer getDayOfWeekId() {
        return dayOfWeekId;
    }

    String getSubjectName() {
        return subjectName;
    }

    int getAttendNum() {
        return attendNum;
    }

    int getAbsentNum() {
        return absentNum;
    }

    int getLateNum() {
        return lateNum;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public int getListId() {
        return listId;
    }

    void setDayOfWeekId(Integer dayOfWeekId) {
        this.dayOfWeekId = dayOfWeekId;
    }

    void setListId(int listId) {
        this.listId = listId;
    }

    void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    void setAttendNum(int attendNum) {
        this.attendNum = attendNum;
    }

    void setAbsentNum(int absentNum) {
        this.absentNum = absentNum;
    }

    void setLateNum(int lateNum) {
        this.lateNum = lateNum;
    }
}
