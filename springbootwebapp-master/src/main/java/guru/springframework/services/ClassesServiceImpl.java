package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.Teacher;
import guru.springframework.repositories.ClassesRepository;
import guru.springframework.repositories.StudentRepository;
import guru.springframework.repositories.TeacherRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private StudentRepository studentRepository;
    public void setClassRepository(ClassesRepository classesRepository){
        this.classesRepository =classesRepository;
    }
    @Autowired
    private TeacherRepository teacherRepository;
    public void setTeacherRepository(TeacherRepository teacherRepository){
        this.teacherRepository =teacherRepository;
    }
    @Override
    public Classes saveClasses(Classes Classes) {
        return classesRepository.save(Classes);
    }
    @Override
    public List<Classes> listAllClass() {
        return classesRepository.findAll();
    }
    @Override
    public Classes getClassesById(Integer id) {
        return classesRepository.findById(id).orElse(null);
    }
    @Override
    public Classes deleteClasses(Integer id) {
        classesRepository.deleteById(id);
        return null;
    }
    @Override
    public Page<Classes> findPaginatied(int pageNo, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize,sort);
        if(keyword!=null){
            return classesRepository.filterClasses(keyword,pageable);
        }
        return classesRepository.findAll(pageable);
    }

    @Override
    public List<Teacher> findHomeRoom(Integer id) {
        return teacherRepository.findHomeRoom(id);
    }

    @Override
    public List<Teacher> listTeacherHomeroom() {
        return classesRepository.listTeacherHomeroom();
    }

//    @Override
//    public List<Student> viewListStudentOfClass(Integer classid) {
////        return studentRepository.filterStudent("",classname,"");
//        return studentRepository.viewListStudentOfClass(classid);
//    }
}
