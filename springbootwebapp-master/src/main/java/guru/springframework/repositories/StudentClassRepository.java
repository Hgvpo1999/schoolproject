package guru.springframework.repositories;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.studentclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<studentclass,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `studentclass`(`studentid`, `classid`) VALUES (:studentid,:classid)",nativeQuery = true)
    public void insertToClass(Integer studentid,Integer classid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM studentclass p WHERE " +
            "p.studentid = :id AND p.classid = :classid")
    public void removeStudent(Integer id, Integer classid);

    @Query(value = "SELECT 1 FROM studentclass p WHERE " +
            "p.studentid = :id")
    studentclass findByStudentId(Integer id);

    @Query(value = "SELECT 1 FROM studentclass sc WHERE " +
            "sc.studentid = :studentid ")
    studentclass getByStudentId(Integer studentid);

    @Query(value = "SELECT p FROM Student p,studentclass sc WHERE " +
            "p.studentid = sc.studentclass")
    List<Student> listStudent();

    @Query(value = "SELECT DISTINCT c FROM Classes c, studentclass sc WHERE " +
            "sc.classid = c.classid")
    List<Classes> listClasses();
//    @Query(value = "SELECT DISTINCT p FROM studentclass sc, students p, classes c WHERE " +
//            "p.studentid = sc.studentid AND sc.classid = c.classid ")
//    List<Student> viewStudentInClass();
//
//    @Query(value = "SELECT DISTINCT c FROM studentclass sc, students p, classes c WHERE " +
//            "p.studentid = sc.studentid AND sc.classid = c.classid ")
//    List<Classes> viewClass();

    @Query(value = "SELECT DISTINCT c FROM studentclass sc,Classes c WHERE " +
            "sc.classid = :classid AND c.classid = sc.classid")
    List<Classes> listClassesById(Integer classid);
    @Query(value = "SELECT DISTINCT p FROM Student p, studentclass sc, Classes c WHERE " +
            "sc.classid = :classid AND p.studentid = sc.studentid" )
    List<Student> listStudentById(Integer classid);

    @Query("SELECT DISTINCT p FROM Student p, studentclass sc WHERE " +
            "sc.classid = :classid AND sc.studentid = p.studentid")
    List<Student> listStudentByClassId(Integer classid);
}
