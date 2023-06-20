package guru.springframework.controllers;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.studentclass;
import guru.springframework.services.ClassesService;
import guru.springframework.services.StudentClassService;
import guru.springframework.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;
    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService =studentService;
    }

    private ClassesService classesService;
    @Autowired
    public void setClassService(ClassesService classesService){
        this.classesService = classesService;
    }

    private StudentClassService studentClassService;
    @Autowired
    public void setStudentClassService(StudentClassService studentClassService){
        this.studentClassService = studentClassService;
    }

    @GetMapping(value = "/students")
    public String list(Model model, Principal principal){
        model.addAttribute("principal",principal);
        String keyword="";
        return findPaginated(1,"studentid","asc",keyword,null,"",model,principal);
    }

    @GetMapping(value = "/studentinfo")
    public String studentInfo(Model model,Principal principal){
        model.addAttribute("students",studentService.listAllStudents());
        model.addAttribute("student",studentService.findOne(Integer.valueOf(principal.getName())));
        model.addAttribute("principal",principal);
        return "studentinfo";
    }
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String showStudent(@PathVariable Integer id, Model model){
        List<Classes> classes = studentService.findOne(id);
        model.addAttribute("classes", classes);
        model.addAttribute("student", studentService.getStudentById(id));
        return "studentshow";
    }

    @RequestMapping(value = "student/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        List<Classes> classes = classesService.listAllClass();
        model.addAttribute("classes", classes);
        List<studentclass> studentclasses = studentClassService.listStudentclass();
        model.addAttribute("studentclasses", studentclasses);
        model.addAttribute("student", studentService.getStudentById(id));
        return "studentform";
    }
    @RequestMapping(value = "student/new",method = RequestMethod.GET)
    public String newStudent(Model model){
        List<Classes> classes = classesService.listAllClass();
        model.addAttribute("classes", classes);
        model.addAttribute("student", new Student());
        return "studentform";
    }
    @PostMapping(value = "student")
    public String saveStudent(Student student,Model model){
        List<Classes> classes = classesService.listAllClass();
        model.addAttribute("classes", classes);
        studentService.saveStudent(student);
        return "redirect:/student/" + student.getStudentid();
    }
    @RequestMapping(value = "student/delete/{id}",method = {RequestMethod.GET,RequestMethod.DELETE})
    public String deleteStudent(@PathVariable Integer id,
                                Model model,Principal principal){
        studentService.deleteStudent(id);
        return findPaginated(1,"studentid","asc","",null,"",model,principal);
    }
    @GetMapping("/student/p/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @Param("sortField") String sortField,
                                @Param("sortDir") String sortDir,
                                @Param("keyword") String keyword,
                                @Param("classname") Integer classname,
                                @Param("status") String status,
                                Model model,Principal principal){
        int pageSize = 5;
        Page<Student> page = studentService.findPaginatied(pageNo,pageSize,sortField,sortDir,keyword,status,classname);
        List<Student> students = page.getContent();
        List<Classes> classes = classesService.listAllClass();
        model.addAttribute("principal",principal);
        model.addAttribute("classes", classes);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("students", students);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("classname", classname);
        model.addAttribute("status", status);
        String reverserSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverserSortDir);
        return "students";
    }
}
