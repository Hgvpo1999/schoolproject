package guru.springframework.repositories;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query("SELECT p FROM Teacher p WHERE " +
            "(p.teachername LIKE CONCAT('%',:keyword, '%') " +
            "Or p.teacherid LIKE CONCAT('%', :keyword, '%')) " +
            "AND p.teachersubject LIKE CONCAT('%', :subject, '%') ")
    public Page<Teacher> filterTeacher(String keyword,String subject, Pageable pageable);
    @Query("SELECT p FROM Teacher p WHERE " +
            "(p.teachername LIKE CONCAT('%', :keyword, '%')" +
            "Or p.teacherid LIKE CONCAT('%', :keyword, '%')) " +
            "AND p.teachersubject LIKE CONCAT('%', :subject, '%') " +
            "AND p.teacherhomeroom = :homeroom")
    public Page<Teacher> filterTeacherByHomeroom(String keyword,String subject,Integer homeroom, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Teacher p, Classes c WHERE " +
            "c.teacherhomeroom = p.teacherid AND c.classid = :id")
    List<Teacher> findHomeRoom(Integer id);

    @Query("SELECT DISTINCT c FROM Classes c WHERE c NOT IN (SELECT cc FROM Classes cc, TeacherClass tc WHERE " +
            "tc.teacherid = :id AND c.classid = tc.classid)")
    List<Classes> viewClassNotTeach(Integer id);

    @Query("SELECT t.teachersubject FROM Teacher t WHERE t.teacherid = :teacherid")
    String getSubjectNameByTeacherId(Integer teacherid);
}
