package com.robop.attendancerecord;

import io.realm.RealmObject;

public class SubjectInfoItems extends RealmObject {

    private Integer subjectId;
    private String subjectName;
    private int attendNum;
    private int absentNum;
    private int lateNum;
    private Integer classId;
    private Integer dayOfWeekId;

    public Integer getSubjectId() {
        return subjectId;
    }

    public Integer getDayOfWeekId() {
        return dayOfWeekId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getAttendNum() {
        return attendNum;
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

    public int getClassId() {
        return classId;
    }

    public void setDayOfWeekId(Integer dayOfWeekId) {
        this.dayOfWeekId = dayOfWeekId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setAttendNum(int attendNum) {
        this.attendNum = attendNum;
    }

    public void setAbsentNum(int absentNum) {
        this.absentNum = absentNum;
    }

    public void setLateNum(int lateNum) {
        this.lateNum = lateNum;
    }
}
