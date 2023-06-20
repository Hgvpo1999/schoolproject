package guru.springframework.repositories;

import guru.springframework.domain.Score;
import guru.springframework.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,String> {
    @Query("SELECT DISTINCT s FROM Subject s,Teacher t WHERE " +
            "t.teachersubject = s.subjectId AND t.teacherid = :teacherid")
    List<Subject> listSubjectByTeacherId(Integer teacherid);

}
