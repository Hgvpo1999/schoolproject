package guru.springframework.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "classes")
@Data
public class Classes {
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "mySeqGen")
//    @SequenceGenerator(name = "mySeqGen",sequenceName = "mySeqGen", initialValue = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classid;
    private String classname;
    private Integer teacherhomeroom;

    public Integer getTeacherhomeroom() {
        return teacherhomeroom;
    }
    public void setTeacherhomeroom(Integer teacherhomeroom) {
        this.teacherhomeroom = teacherhomeroom;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
