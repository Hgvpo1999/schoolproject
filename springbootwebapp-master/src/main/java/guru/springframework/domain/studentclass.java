package guru.springframework.domain;

import javax.persistence.*;

@Entity
@Table(name = "studentclass")
public class studentclass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentclass;
    private Integer studentid;
    private Integer classid;

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getClassid() {
        return classid;
    }

    public Integer getStudentclass() {
        return studentclass;
    }

    public void setStudentclass(Integer studentclass) {
        this.studentclass = studentclass;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }
}
