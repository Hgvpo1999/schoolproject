package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassesService {
    Classes saveClasses(Classes Classes);
    List<Classes> listAllClass();
    Classes getClassesById(Integer id);
    Classes deleteClasses(Integer id);
    Page<Classes> findPaginatied(int pageNo, int pageSize, String sortField, String sortDir, String keyword);

    List<Teacher> findHomeRoom(Integer id);

    List<Teacher> listTeacherHomeroom();

//    List<Student> viewListStudentOfClass(Integer classid);
}
