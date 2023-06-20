package guru.springframework.controllers;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.studentclass;
import guru.springframework.services.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentClassController {
    private StudentClassService studentClassService;
    @Autowired
    public void setStudentClassService(StudentClassService studentClassService){this.studentClassService =studentClassService;}

    @GetMapping(value = "studentclass")
    public String viewList(Model model){
        var studentClasses = new ArrayList<StudentClass>();
        List<Classes> classes = studentClassService.listClasses();
        for (Classes classes1 : classes){
            List<Student> students = studentClassService.listStudentByClassId(classes1.getClassid());
            for (Student student : students){
                StudentClass studentClass = new StudentClass();
                studentClass.studentId = student.getStudentid();
                studentClass.studentName = student.getStudentname();
                studentClass.classId = classes1.getClassid();
                studentClass.className = classes1.getClassname();
                studentClasses.add(studentClass);
            }
        }
        model.addAttribute("studentClasses",studentClasses);
        return "studentclass";
    }
    static class StudentClass {
        public Integer studentId;
        public Integer classId;
        public String studentName;
        public String className;
    }

}
