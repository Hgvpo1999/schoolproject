package guru.springframework.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scoreid")
    private Integer scoreId;
    @Column(name = "studentid")
    private Integer studentId;
    @Column(name = "subjectid")
    private String subjectId;

    @Column(name = "midscore")
    private Float midScore;

    @Column(name = "finalscore")
    private Float finalScore;

    @Column(name = "averagescore")
    private Float averageScore;
    @Transient
    private String studentName;
    @Transient
    private String subjectName;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Float getMidScore() {
        return midScore;
    }

    public void setMidScore(Float midScore) {
        this.midScore = midScore;
    }

    public Float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Float finalScore) {
        this.finalScore = finalScore;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }
}
