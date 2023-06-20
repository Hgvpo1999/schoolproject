package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Teacher;
import guru.springframework.repositories.TeacherClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherClassServiceImpl implements TeacherClassService{

    @Autowired
    private TeacherClassRepository teacherClassRepository;
    @Override
    public List<Classes> viewClass(Integer id) {
        return teacherClassRepository.viewClass(id);
    }

    @Override
    public List<Teacher> viewTeacher(Integer id) {
        return teacherClassRepository.viewTeacher(id);
    }

    @Override
    public void deleteClass(Integer teacherid, Integer classid) {
        teacherClassRepository.deleteByTeacherIdAndClassId(teacherid,classid);
    }
}
