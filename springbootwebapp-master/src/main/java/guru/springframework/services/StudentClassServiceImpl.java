package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.studentclass;
import guru.springframework.repositories.StudentClassRepository;
import guru.springframework.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassServiceImpl implements StudentClassService{
    @Autowired
    private StudentClassRepository studentClassRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public studentclass saveStudentClass(studentclass studentclass) {
        return studentClassRepository.save(studentclass);
    }
    @Override
    public studentclass insertStudentToClass(Integer studentid, Integer classid,Integer studentactivate) {
        if(studentactivate == 1){
            studentClassRepository.insertToClass(studentid,classid);
        } else {
            return null;
        }
        return null;
    }

    @Override
    public void delete(Integer id, Integer classid) {
        studentClassRepository.removeStudent(id,classid);
    }

    @Override
    public List<studentclass> listStudentclass() {
        return studentClassRepository.findAll();
    }

    @Override
    public studentclass findByStudentId(Integer id) {
        return studentClassRepository.findByStudentId(id);
    }

    @Override
    public studentclass getByStudenId(Integer studentid) {
        return studentClassRepository.getByStudentId(studentid);
    }

    @Override
    public List<studentclass> listAll() {
        return studentClassRepository.findAll();
    }

    @Override
    public List<Student> listStudent() {
        return studentClassRepository.listStudent();
    }

    @Override
    public List<Classes> listClasses(){
        return studentClassRepository.listClasses();
    }

    @Override
    public List<Classes> listClassesById(Integer classid) {
        return studentClassRepository.listClassesById(classid);
    }

    @Override
    public List<Student> listStudentById(Integer studentid) {
        return studentClassRepository.listStudentById(studentid);
    }

    @Override
    public List<Student> listStudentByClassId(Integer classid) {
        return studentClassRepository.listStudentByClassId(classid);
    }

//    @Override
//    public List<studentclass> listStudentclass() {
//        return studentClassRepository.findAll();
//    }
//    @Override
//    public List<Student> viewStudentInClass() {
//        return studentClassRepository.viewStudentInClass();
//    }
//
//    @Override
//    public List<Classes> viewClass() {
//        return studentClassRepository.viewClass();
//    }

}
