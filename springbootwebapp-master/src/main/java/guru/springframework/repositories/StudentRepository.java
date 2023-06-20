package guru.springframework.repositories;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("SELECT p FROM Student p WHERE " +
            "(p.studentname LIKE CONCAT('%',:keyword, '%') " +
            "Or p.studentid LIKE CONCAT('%', :keyword, '%')) " +
            "AND p.studentstatus LIKE CONCAT('%', :status, '%') ")
    public Page<Student> filterStudent(String keyword,String status,Pageable pageable);
    @Query("SELECT p FROM Student p, studentclass sc WHERE " +
            "(p.studentname LIKE CONCAT('%',:keyword, '%') " +
            "Or p.studentid LIKE CONCAT('%', :keyword, '%')) " +
            "AND p.studentstatus LIKE CONCAT('%', :status, '%') " +
            "AND p.studentid = sc.studentid " +
            "AND sc.classid = :classname")
    public Page<Student> filterStudentByClass(String keyword,String status,Integer classname,Pageable pageable);
    @Query("SELECT DISTINCT p FROM Student p, Classes c, studentclass sc WHERE " +
            "p.studentid = sc.studentid AND sc.classid = :studentid")
    List<Student> findStudentByClass(Integer studentid);
    @Query("SELECT DISTINCT c FROM Student p, Classes c ,studentclass sc WHERE " +
            "sc.studentid = :id AND sc.classid = c.classid")
    List<Classes> findOne(Integer id);
    @Query("SELECT DISTINCT p FROM Student p WHERE " +
            "p NOT IN (SELECT DISTINCT p FROM Student p, Classes c, studentclass sc WHERE " +
            "p.studentid = sc.studentid AND sc.classid = :classid) " +
            "AND p.studentactivate = 1")
    List<Student> findStudentNotInClass(Integer classid);
    @Query(value = "INSERT INTO studentclass (studentid,classid) VALUES (:studentid,:classid) ", nativeQuery = true)
    void insertToClass(Integer studentid, Integer classid);

    @Query("SELECT p FROM Student p, Score s, Subject sj "
//            +" WHERE p.studentid = s.studentId AND sj.subjectId = s.subjectId"
    )
    List<Student> listAllStudentInScore();
}
