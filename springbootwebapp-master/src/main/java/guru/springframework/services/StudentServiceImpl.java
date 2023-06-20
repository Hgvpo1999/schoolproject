package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    public void setProductRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public Iterable<Student> listAllStudents() {
        return studentRepository.findAll();
    }
    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public Student deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        return null;
    }
    @Override
    public List<Student> findAllWithSort(String field) {
        return studentRepository.findAll(Sort.by(field));
    }
    @Override
    public Page<Student> findPaginatied(int pageNo, int pageSize,String sortField, String sortDir,
                                        String keyword,String status,Integer classname) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize,sort);

        if(classname==null) {
            if (keyword != null || status != null) {
                return studentRepository.filterStudent(keyword, status, pageable);
            }
            return this.studentRepository.findAll(pageable);
        } else {
            return studentRepository.filterStudentByClass(keyword, status,classname, pageable);
        }

    }
    @Override
    public List<Student> getAllStudent(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));

        Page<Student> pageResult = studentRepository.findAll(paging);

        if(pageResult.hasContent()){
            return pageResult.getContent();
        } else {
            return new ArrayList<Student>();
        }
    }

    @Override
    public List<Student> listAllByClass(Integer classid) {
        return studentRepository.findStudentByClass(classid);
    }

    @Override
    public List<Student> listAllStudentsNotInClass(Integer classid) {
        return studentRepository.findStudentNotInClass(classid);
    }

    @Override
    public void insertToClass(Integer studentid, Integer classid) {
        studentRepository.insertToClass(studentid,classid);
    }

    @Override
    public List<Classes> findOne(Integer id) {
        return studentRepository.findOne(id);
    }

    @Override
    public List<Student> listAllStudentInScore() {
        return studentRepository.listAllStudentInScore();
    }

}
