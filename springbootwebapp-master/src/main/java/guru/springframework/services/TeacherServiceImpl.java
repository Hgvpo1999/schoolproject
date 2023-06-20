package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Teacher;
import guru.springframework.domain.TeacherClass;
import guru.springframework.repositories.TeacherClassRepository;
import guru.springframework.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Autowired
    private TeacherClassRepository teacherClassRepository;
    private void setTeacherClassRepository(TeacherClassRepository teacherClassRepository){
        this.teacherClassRepository = teacherClassRepository;
    }
    @Override
    public Iterable<Teacher> listAllTeachers() {
        return teacherRepository.findAll();
    }
    @Override
    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id).orElse(null);
    }
    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    @Override
    public Teacher deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
        return null;
    }
    @Override
    public List<Teacher> findAllWithSort(String field) {
        return teacherRepository.findAll(Sort.by(field));
    }
    @Override
    public Page<Teacher> findPaginatied(int pageNo, int pageSize, String sortField, String sortDir,
                                        String keyword, String subject, Integer homeroom) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize,sort);

        if(homeroom == null){
            if(keyword!=null || subject!=null){
                return teacherRepository.filterTeacher(keyword, subject, pageable);
            }
            return this.teacherRepository.findAll(pageable);
        } else {
            return teacherRepository.filterTeacherByHomeroom(keyword, subject, homeroom, pageable);
        }
    }

    @Override
    public List<Classes> viewClassNotTeach(Integer id) {
        if(id != null)
        {
            return teacherRepository.viewClassNotTeach(id);
        }
        return null;
    }

    @Override
    public TeacherClass addClassNotTeach(Integer teacherId,Integer classId) {
        if(teacherId != null && classId != null){
            teacherClassRepository.addClassNotTeach(teacherId,classId);
        } else {
            return null;
        }
        return null;
    }

    @Override
    public String getSubjectNameByTeacherId(Integer teacherid) {
        if(teacherid !=null) {
            return teacherRepository.getSubjectNameByTeacherId(teacherid);
        }
        return null;
    }

}
