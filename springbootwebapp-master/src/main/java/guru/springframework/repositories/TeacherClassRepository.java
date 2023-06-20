package guru.springframework.repositories;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Teacher;
import guru.springframework.domain.TeacherClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherClassRepository extends JpaRepository<TeacherClass,Integer> {

    @Query(value = "SELECT c FROM Classes c, TeacherClass tc WHERE " +
            "c.classid = tc.classid AND tc.teacherid = :id")
    List<Classes> viewClass(Integer id);

    @Query(value = "SELECT t FROM Teacher t, TeacherClass tc WHERE " +
            "t.teacherid = tc.teacherid AND tc.classid = :id")
    List<Teacher> viewTeacher(Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `teachersclasses`(`teacherid`, `classid`) VALUES (:teacherId,:classId)",nativeQuery = true)
    void addClassNotTeach(Integer teacherId, Integer classId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TeacherClass tc WHERE " +
            "tc.teacherid = :teacherid AND tc.classid = :classid")
    void deleteByTeacherIdAndClassId(Integer teacherid, Integer classid);
}
