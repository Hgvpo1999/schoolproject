package guru.springframework.repositories;

import guru.springframework.domain.Score;
import guru.springframework.domain.Student;
import guru.springframework.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    @Query("SELECT DISTINCT s FROM Subject s")
    List<Subject> listSubject();
    @Query("SELECT DISTINCT s.midScore FROM Score s WHERE " +
            "s.studentId = :studentid AND s.subjectId = :subjectId")
    Float getMidScoreByStudentIdAndSubjectId(Integer studentid, String subjectId);
    @Query("SELECT DISTINCT s.finalScore FROM Score s WHERE " +
            "s.studentId = :studentid AND s.subjectId = :subjectId")
    Float getFinalScoreByStudentIdAndSubjectId(Integer studentid, String subjectId);
    @Query("SELECT DISTINCT s.averageScore FROM Score s WHERE " +
            "s.studentId = :studentid AND s.subjectId = :subjectId")
    Float getAverageScoreByStudentIdAndSubjectId(Integer studentid, String subjectId);

    @Query("UPDATE Score SET midScore =':midScore', finalScore=':finalScore' , averageScore=':averageScore' " +
            "WHERE studentId = :studentId AND subjectId = :subjectId")
    Score updateScore(Integer studentId,String subjectId,Float midScore,Float finalScore,Float averageScore);

    @Query("SELECT DISTINCT s FROM TeacherClass tc,Classes c, studentclass sc, Student s WHERE " +
            "tc.teacherid = :teacherid AND tc.classid = c.classid AND c.classid = sc.classid AND " +
            "sc.studentid = s.studentid ")
    List<Student> listStudentInClassWasTeach(Integer teacherid);

    @Query("SELECT DISTINCT s FROM Score s,TeacherClass tc, Classes c, studentclass sc,Student st WHERE " +
            "s.subjectId = :teachersubject AND tc.teacherid = :teacherid AND tc.classid = c.classid AND " +
            "sc.studentid = st.studentid AND sc.classid = c.classid AND s.studentId = st.studentid")
    List<Score> listBySubjectIdAndTeacherId(Integer teacherid, String teachersubject);

    @Query("SELECT DISTINCT s FROM Score s WHERE " +
            "s.studentId = :studentId AND s.subjectId = :subjectId")
    List<Score> listByStudentIdAndSubjectId(Integer studentId, String subjectId);

    @Query("SELECT s FROM Score s WHERE " +
            "s.studentId = :studentId AND s.subjectId = :subjectId ")
    Score getScoreByStudentIdAndSubjectId(Integer studentId, String subjectId);

    @Query("SELECT s FROM Score s WHERE " +
            "s.studentId LIKE CONCAT('%', :keyword, '%') " +
            "AND s.subjectId LIKE CONCAT('%', :subjectId, '%') ")
    public Page<Score> filterScore(String keyword, String subjectId, Pageable pageable);
}
