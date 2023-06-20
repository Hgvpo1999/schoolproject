package guru.springframework.repositories;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes,Integer> {
    @Query("SELECT p FROM Classes p WHERE " +
            "p.classname LIKE CONCAT('%', :keyword, '%')" +
            "Or p.classid LIKE CONCAT('%', :keyword, '%')")
    Page<Classes> filterClasses(String keyword, Pageable pageable);

    @Query("SELECT t FROM Teacher t WHERE " +
            "t.teacherhomeroom = 1 AND t.teacherid NOT IN (SELECT t FROM Teacher t, Classes c WHERE " +
            "t.teacherid = c.teacherhomeroom)")
    List<Teacher> listTeacherHomeroom();
}
