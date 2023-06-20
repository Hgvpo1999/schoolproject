package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.studentclass;

import java.util.List;

public interface StudentClassService {
    studentclass saveStudentClass(studentclass studentclass);
    studentclass insertStudentToClass(Integer studentid,Integer classid,Integer studentactivate);
    void delete(Integer id, Integer classid);
    List<studentclass> listStudentclass();

    studentclass findByStudentId(Integer id);

    studentclass getByStudenId(Integer studentid);

    List<studentclass> listAll();

    List<Student> listStudent();
    List<Classes> listClasses();

    List<Classes> listClassesById(Integer classid);
    List<Student> listStudentById(Integer studentid);

    List<Student> listStudentByClassId(Integer classid);
}
