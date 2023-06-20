package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.Teacher;
import guru.springframework.domain.TeacherClass;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    Iterable<Teacher> listAllTeachers();
    Teacher getTeacherById(Integer id);
    Teacher saveTeacher(Teacher teacher);
    Teacher deleteTeacher(Integer id);
    public List<Teacher> findAllWithSort(String field);
    public Page<Teacher> findPaginatied(int pageNo,int pageSize,String sortField,String sortDir,String keyword,
                                 String subject,Integer homeroom);
    List<Classes> viewClassNotTeach(Integer id);

    TeacherClass addClassNotTeach(Integer studentId,Integer classId);

    String getSubjectNameByTeacherId(Integer teacherid);
}
