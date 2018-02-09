package com.robop.attendancerecord;

import io.realm.RealmObject;

public class SubjectInfoItems extends RealmObject {

    private Integer subjectId;
    private Integer dayOfweekId;
    private String subjectName;
    private int absentNum;
    private int lateNum;

    public Integer getSubjectId() {
        return subjectId;
    }

    public Integer getDayOfweekId() {
        return dayOfweekId;
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

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public void setDayOfweekId(Integer dayOfweekId) {
        this.dayOfweekId = dayOfweekId;
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
