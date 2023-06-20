package guru.springframework.services;

import guru.springframework.domain.Score;
import guru.springframework.domain.Student;
import guru.springframework.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScoreService {
    Score saveScore(Score score);
    List<Subject> listSubject();

    Float getMidScoreByStudentIdAndSubjectId(Integer studentid, String subjectId);
    Float getFinalScoreByStudentIdAndSubjectId(Integer studentid, String subjectId);
    Float getAverageScoreByStudentIdAndSubjectId(Integer studentid, String subjectId);

    Score updateScore(Score score);

    List<Student> listStudentInClassWasTeach(Integer teacherid);

    Optional<Score> getScoreById(Integer id);
    void saveAll(List<Score> scores);

    List<Score> listBySubjectIdAndTeacherId(Integer teacherid, String teachersubject);

    List<Score> listByStudentIdAndSubjectId(Integer studentId, String subjectId);
    Score getScoreByStudentIdAndSubjectId(Integer studentId, String subjectId);

    public Page<Score> findPaginatied(int pageNo, int pageSize, String sortField, String sortDir, String keyword, String subjectId);

}
