package guru.springframework.services;

import guru.springframework.domain.Score;
import guru.springframework.domain.Student;
import guru.springframework.domain.Subject;
import guru.springframework.repositories.ScoreRepository;
import guru.springframework.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService{
    @Autowired
    private ScoreRepository scoreRepository;
    public void setScoreRepository(ScoreRepository scoreRepository){this.scoreRepository = scoreRepository;}
    @Autowired
    private SubjectRepository subjectRepository;
    public void setSubjectRepository(SubjectRepository subjectRepository){this.subjectRepository = subjectRepository;}
    @Override
    public Score saveScore(Score score) {
        if(score != null) {
            return scoreRepository.save(score);
        }
        return null;
    }

    @Override
    public List<Subject> listSubject() {
        return scoreRepository.listSubject();
    }

    @Override
    public Float getMidScoreByStudentIdAndSubjectId(Integer studentid, String subjectId) {
        return scoreRepository.getMidScoreByStudentIdAndSubjectId(studentid,subjectId);
    }

    @Override
    public Float getFinalScoreByStudentIdAndSubjectId(Integer studentid, String subjectId) {
        return scoreRepository.getFinalScoreByStudentIdAndSubjectId(studentid,subjectId);
    }

    @Override
    public Float getAverageScoreByStudentIdAndSubjectId(Integer studentid, String subjectId) {
        return scoreRepository.getAverageScoreByStudentIdAndSubjectId(studentid,subjectId);
    }

    @Override
    public Score updateScore(Score score) {
        if(score != null) {
            Float averageScore = (float) (score.getMidScore()*0.3 + score.getFinalScore()*0.7);
            return scoreRepository.updateScore(score.getStudentId(),score.getSubjectId(),score.getMidScore(),score.getFinalScore(),averageScore);
        }
         return null;
    }

    @Override
    public List<Student> listStudentInClassWasTeach(Integer teacherid) {
        if(teacherid != null){
            return scoreRepository.listStudentInClassWasTeach(teacherid);
        }
        return null;
    }

    @Override
    public Optional<Score> getScoreById(Integer id) {
        if(id != null){
            return scoreRepository.findById(id);
        }
        return null;
    }

    @Override
    public void saveAll(List<Score> scores) {
        scoreRepository.saveAll(scores);
    }

    @Override
    public List<Score> listBySubjectIdAndTeacherId(Integer teacherid, String teachersubject) {
        if (teacherid!=null){
            return scoreRepository.listBySubjectIdAndTeacherId(teacherid,teachersubject);
        }
        return null;
    }

    @Override
    public List<Score> listByStudentIdAndSubjectId(Integer studentId, String subjectId) {
        if(studentId!=null){
            return scoreRepository.listByStudentIdAndSubjectId(studentId,subjectId);
        }
        return null;
    }

    @Override
    public Score getScoreByStudentIdAndSubjectId(Integer studentId, String subjectId) {
        if(studentId!=null){
            scoreRepository.getScoreByStudentIdAndSubjectId(studentId,subjectId);
        }
        return null;
    }

    @Override
    public Page<Score> findPaginatied(int pageNo, int pageSize, String sortField,
                                      String sortDir, String keyword, String subjectId) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize,sort);
        if(keyword!=null||subjectId!=null){
            return scoreRepository.filterScore(keyword,subjectId,pageable);
        }
        return scoreRepository.findAll(pageable);
    }
}
