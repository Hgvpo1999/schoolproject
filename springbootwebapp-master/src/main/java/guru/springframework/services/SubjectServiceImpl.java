package guru.springframework.services;

import guru.springframework.domain.Subject;
import guru.springframework.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;
    public void setSubjectRepository(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }
    @Override
    public List<Subject> listAll() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> listSubjectByTeacherId(Integer teacherid) {
        if(teacherid !=null){
            return subjectRepository.listSubjectByTeacherId(teacherid);
        }
        return null;
    }
}
