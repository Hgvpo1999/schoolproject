package guru.springframework.services;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Teacher;

import java.util.List;

public interface TeacherClassService {
    List<Classes> viewClass(Integer id);

    List<Teacher> viewTeacher(Integer id);

    void deleteClass(Integer teacherid, Integer classid);

}
