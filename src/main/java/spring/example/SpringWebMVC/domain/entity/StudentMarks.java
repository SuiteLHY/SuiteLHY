package spring.example.SpringWebMVC.domain.entity;

import java.io.Serializable;

public class StudentMarks extends Student
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer marks;

    private Integer year;

    private Integer sid;

    @Override
    public String toString() {
        return isEmpty() ? "{}" : "{id=" + super.getId()
                + ", name=" + super.getName()
                + ", age=" + super.getAge()
                + ", marks=" + marks
                + ", year=" + year
                + ", sid=" + sid
                + "}";
    }

    //===== getter and setter =====//
    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

}
