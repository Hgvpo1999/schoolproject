package guru.springframework.controllers;

import guru.springframework.domain.*;
import guru.springframework.repositories.ScoreRepository;
import guru.springframework.repositories.SubjectRepository;
import guru.springframework.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ScoreController {
    private ScoreService scoreService;
    @Autowired
    public void setScoreService(ScoreService scoreService){
        this.scoreService = scoreService;
    }
    private ClassesService classesService;
    @Autowired
    public void setClassesService(ClassesService classesService){
        this.classesService = classesService;
    }
    private StudentService studentService;
    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }
    private SubjectService subjectService;
    @Autowired
    public void setSubjectService(SubjectService subjectService){
        this.subjectService = subjectService;
    }
    private TeacherService teacherService;
    @Autowired
    public void setTeacherService(TeacherService teacherService){
        this.teacherService = teacherService;
    }
    @Autowired
    private SubjectRepository subjectRepository;

    static class TeacherScore{
        public Integer teacherId;
        public Integer studentId;
        public Integer classId;
        public String subjectId;
        public String studentName;
        public String subjectName;
        public String className;
        public Float midScore;
        public Float finalScore;
        public Float averageScore;
        public Integer scoreId;

        public Integer getScoreId() {
            return scoreId;
        }

        public void setScoreId(Integer scoreId) {
            this.scoreId = scoreId;
        }

        public Integer getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(Integer teacherId) {
            this.teacherId = teacherId;
        }

        public Integer getStudentId() {
            return studentId;
        }

        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }

        public Integer getClassId() {
            return classId;
        }

        public void setClassId(Integer classId) {
            this.classId = classId;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getClassName() {
            return className;
        }
        public void setClassName(String className) {
            this.className = className;
        }

        public Float getMidScore() {
            return midScore;
        }

        public void setMidScore(Float midScore) {
            this.midScore = midScore;
        }

        public Float getFinalScore() {
            return finalScore;
        }
        public void setFinalScore(Float finalScore) {
            this.finalScore = finalScore;
        }

        public Float getAverageScore() {
            return averageScore;
        }

        public void setAverageScore(Float averageScore) {
            this.averageScore = averageScore;
        }
    }

    @GetMapping("scores")
    public String viewScore(Model model){
        return findPaginated(1,"studentId","asc","","",model);
    }
    @GetMapping("/score/p/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @Param("sortField") String sortField,
                                @Param("sortDir") String sortDir,
                                @Param("keyword") String keyword,
                                @Param("subjectName") String subjectId,
                                Model model){
        int pageSize = 7;
        Page<Score> page = scoreService.findPaginatied(pageNo,pageSize,sortField,sortDir,keyword,subjectId);
        List<Score> scores = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("scores", scores);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("subjectId", subjectId);
        String reverserSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverserSortDir);
        return "scores";
    }

    @GetMapping("score/student/{id}")
    public String listScoreStudent(@PathVariable("id") Integer id,Model model){
        var teacherScores = new ArrayList<TeacherScore>();
        Student student = studentService.getStudentById(id);
        List<Subject> subjects = subjectService.listAll();
        for (Subject subject:subjects){
            TeacherScore teacherScore = new TeacherScore();
            teacherScore.studentName = student.getStudentname();
            teacherScore.subjectName = subject.getSubjectName();
            teacherScore.midScore = scoreService.getMidScoreByStudentIdAndSubjectId(student.getStudentid(),subject.getSubjectId());
            teacherScore.finalScore = scoreService.getFinalScoreByStudentIdAndSubjectId(student.getStudentid(),subject.getSubjectId());
            teacherScore.averageScore = scoreService.getAverageScoreByStudentIdAndSubjectId(student.getStudentid(),subject.getSubjectId());
            teacherScores.add(teacherScore);
        }
        model.addAttribute("scoreClasses", teacherScores);
        return "studentScore";
    }
    @GetMapping("score/classes/{id}")
    public String listScoreClass(@PathVariable("id") Integer id, Model model){
        var teacherScores = new ArrayList<TeacherScore>();
        List<Student> students = studentService.listAllByClass(id);
        for (Student student : students){
            List<Subject> subjects = subjectService.listAll();
            for (Subject subject : subjects){
                    TeacherScore teacherScore = new TeacherScore();
//                    teacherScore.setScoreId( (scoreService.getScoreByStudentIdAndSubjectId(student.getStudentid(),subject.getSubjectId())).getScoreId());
                    teacherScore.studentId = student.getStudentid();
                    teacherScore.studentName = student.getStudentname();
                    teacherScore.subjectName = subject.getSubjectName();
                    teacherScore.midScore = scoreService.getMidScoreByStudentIdAndSubjectId(student.getStudentid(), subject.getSubjectId());
                    teacherScore.finalScore = scoreService.getFinalScoreByStudentIdAndSubjectId(student.getStudentid(), subject.getSubjectId());
                    teacherScore.averageScore = scoreService.getAverageScoreByStudentIdAndSubjectId(student.getStudentid(), subject.getSubjectId());
                    teacherScores.add(teacherScore);
            }
        }
        model.addAttribute("scoreClasses",teacherScores);
        return "scoreclass";
        }
    @GetMapping(value = "/score/teacher/{id}")
    public String getScoreTeacher(@PathVariable("id") Integer id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        List<Score> scores = scoreService.listBySubjectIdAndTeacherId(teacher.getTeacherid(), teacher.getTeachersubject());

        List<TeacherScore> teacherScores = new ArrayList<>();
        for (Score score : scores) {
            TeacherScore teacherScore = new TeacherScore();
            teacherScore.setScoreId(score.getScoreId());
            teacherScore.setStudentId(score.getStudentId());
            teacherScore.setSubjectId(score.getSubjectId());
            teacherScore.setStudentName(studentService.getStudentById(score.getStudentId()).getStudentname());
            teacherScore.setSubjectName(subjectRepository.findById(score.getSubjectId()).get().getSubjectName());
            teacherScore.setMidScore(score.getMidScore());
            teacherScore.setFinalScore(score.getFinalScore());
            teacherScore.setAverageScore(score.getAverageScore());
            teacherScores.add(teacherScore);
        }
        model.addAttribute("scores", teacherScores);
        model.addAttribute("teacher", teacher);
        return "scoreteacher";
    }
    @PostMapping(value = "/score/save")
    public ResponseEntity<?> saveScore(@RequestParam("scoreId") Integer scoreId,
                                            @RequestParam("midScore") Float midScore,
                                            @RequestParam("finalScore") Float finalScore) {
        Optional<Score> scoreOptional = scoreService.getScoreById(scoreId);
        if (scoreOptional.isPresent()) {
            Score score = scoreOptional.get();
            score.setMidScore(midScore);
            score.setFinalScore(finalScore);
            score.setAverageScore((float) (midScore * 0.3 + finalScore * 0.7));
            scoreService.saveScore(score);
            return ResponseEntity.status(HttpStatus.OK).body("Score save success!");
        } else {
            return ResponseEntity.badRequest().body("Invalid score ID");
        }
    }
}
