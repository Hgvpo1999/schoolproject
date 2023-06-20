package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentService {
    Iterable<Student> listAllStudents();
    Student getStudentById(Integer id);
    Student saveStudent(Student student);
    Student deleteStudent(Integer id);
    public List<Student> findAllWithSort(String field);
    public Page<Student> findPaginatied(int pageNo, int pageSize,String sortField,String sortDir,String keyword,String status,Integer classname);

    List<Student> getAllStudent(Integer pageNo, Integer pageSize, String sortBy);
    List<Student> listAllByClass(Integer classid);
    List<Student> listAllStudentsNotInClass(Integer classid);
    void insertToClass(Integer studentid, Integer classid);
    List<Classes> findOne(Integer id);
    List<Student> listAllStudentInScore();
}