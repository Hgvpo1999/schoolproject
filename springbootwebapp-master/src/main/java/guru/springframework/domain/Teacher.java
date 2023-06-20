package guru.springframework.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherid;
    private String teachername;
    private Date teacherdob;
    private String teachergender;
    private String teacheraddress;
    private String teachermail;
    private String teachersubject;
    @Column(name = "teacherhomeroom")
    private Integer teacherhomeroom;
    @PrePersist
    private void onLoad(){
        teacherhomeroom = 0;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public Date getTeacherdob() {
        return teacherdob;
    }

    public void setTeacherdob(Date teacherdob) {
        this.teacherdob = teacherdob;
    }

    public String getTeachergender() {
        return teachergender;
    }

    public void setTeachergender(String teachergender) {
        this.teachergender = teachergender;
    }

    public String getTeacheraddress() {
        return teacheraddress;
    }

    public void setTeacheraddress(String teacheraddress) {
        this.teacheraddress = teacheraddress;
    }

    public String getTeachermail() {
        return teachermail;
    }

    public void setTeachermail(String teachermail) {
        this.teachermail = teachermail;
    }

    public String getTeachersubject() {
        return teachersubject;
    }

    public void setTeachersubject(String teachersubject) {
        this.teachersubject = teachersubject;
    }

    public Integer getTeacherhomeroom() {
        return teacherhomeroom;
    }

    public void setTeacherhomeroom(Integer teacherhomeroom) {
        this.teacherhomeroom = teacherhomeroom;
    }
}
