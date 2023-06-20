package guru.springframework.domain;

import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentid;
    private String studentname;
    private Date studentdob;
    private String studentgender;
    private String studentstatus;
    private Integer studentactivate; //1 = activate, 0 = deactivate
    @PrePersist
    void onLoad(){
        studentactivate = 0;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public Date getStudentdob() {
        return studentdob;
    }

    public void setStudentdob(Date studentdob) {
        this.studentdob = studentdob;
    }

    public String getStudentgender() {
        return studentgender;
    }

    public void setStudentgender(String studentgender) {
        this.studentgender = studentgender;
    }

    public String getStudentstatus() {
        return studentstatus;
    }
    public void setStudentstatus(String studentstatus) {
        this.studentstatus = studentstatus;
    }

    public Integer getStudentactivate() {
        return studentactivate;
    }

    public void setStudentactivate(Integer studentactivate) {
        this.studentactivate = studentactivate;
    }
}
