package guru.springframework.services;

import guru.springframework.domain.Score;
import guru.springframework.domain.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubjectService {
    List<Subject> listAll();
    List<Subject> listSubjectByTeacherId(Integer teacherid);

}
