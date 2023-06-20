package guru.springframework.domain;

import javax.persistence.*;
@Entity
@Table(name = "teachersclasses")
public class TeacherClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherclass;
    private Integer teacherid;
    private Integer classid;

    public Integer getTeacherclass() {
        return teacherclass;
    }

    public void setTeacherclass(Integer teacherclass) {
        this.teacherclass = teacherclass;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

}
