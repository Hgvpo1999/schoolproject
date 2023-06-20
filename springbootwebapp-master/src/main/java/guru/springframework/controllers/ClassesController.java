package guru.springframework.controllers;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Student;
import guru.springframework.domain.Teacher;
import guru.springframework.domain.studentclass;
import guru.springframework.services.ClassesService;
import guru.springframework.services.StudentClassService;
import guru.springframework.services.StudentService;
import guru.springframework.services.TeacherClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClassesController {
    private ClassesService classesService;
    @Autowired
    public void setClassService(ClassesService classesService){
        this.classesService = classesService;
    }
    private TeacherClassService teacherClassService;
    @Autowired
    public void setTeacherClassService(TeacherClassService teacherClassService){
        this.teacherClassService = teacherClassService;
    }
    private StudentService studentService;
    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }
    private StudentClassService studentClassService;
    @Autowired
    public void setStudentClassService(StudentClassService studentClassService){
        this.studentClassService = studentClassService;
    }
    @GetMapping(value = "/classes")
    public String list(Model model){

        return findPaginated(1,"classid","asc","",model);
    }
    @GetMapping(value = "/classes/{id}")
    public String showClass(@PathVariable Integer id, Model model){
        List<Teacher> teachers = classesService.findHomeRoom(id);
        model.addAttribute("teachers", teachers);
        List<Teacher> teachers1 = teacherClassService.viewTeacher(id);
        model.addAttribute("teacherss", teachers1);
        model.addAttribute("classes", classesService.getClassesById(id));
        return "classshow";
    }
    @GetMapping(value = "/classes/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        List<Teacher> teachers = classesService.listTeacherHomeroom();
        model.addAttribute("teachers",teachers);
        model.addAttribute("classes", classesService.getClassesById(id));
        return "classform";
    }
    @GetMapping(value = "classes/viewlist/{id}")
    public String viewList(@PathVariable Integer id, Model model){
        List<Student> students = studentService.listAllByClass(id);
        model.addAttribute("students", students);
        model.addAttribute("classes", classesService.getClassesById(id));
        return "classlist";
    }
    @GetMapping(value = "/classes/viewlist/{id}/add")
    public String addToClass(@PathVariable Integer id, Model model){
        List<Student> students = studentService.listAllStudentsNotInClass(id);
        model.addAttribute("students", students);
        model.addAttribute("classes", classesService.getClassesById(id));
        return "classlistadd";
    }
    @RequestMapping(value = "/classes/viewlist/{classid}/add/{studentid}/{studentactivate}",method = {RequestMethod.GET,RequestMethod.POST})
    public String addStudentToClass(@PathVariable Integer classid,
                                    @PathVariable Integer studentid,
                                    @PathVariable Integer studentactivate, Model model){
        model.addAttribute("classes", classesService.getClassesById(classid));
        List<Student> students = studentService.listAllStudentsNotInClass(classid);
        model.addAttribute("students", students);
        studentClassService.insertStudentToClass(studentid,classid,studentactivate);
        return "redirect:/classes/viewlist/{classid}/add";

    }
    @RequestMapping(value = "/classes/viewlist/{classid}/{studentid}",method = {RequestMethod.GET,RequestMethod.DELETE})
    public String removeStudent(@PathVariable Integer classid,@PathVariable Integer studentid,Model model){
        List<Student> students = studentService.listAllByClass(classid);
        model.addAttribute("students",students);
        model.addAttribute("classes",classesService.getClassesById(classid));
        studentClassService.delete(studentid,classid);
        return "redirect:/classes/viewlist/{classid}";
    }

    @RequestMapping(value = "/classes/new")
    public String newClasses(Model model){
        model.addAttribute("classes", new Classes());
        return "classform";
    }
    @PostMapping(value = "classes")
    public String saveClasses(Classes classes){
        classesService.saveClasses(classes);
        return "redirect:/classes/" + classes.getClassid();
    }
    @RequestMapping(value = "classes/delete/{id}",method = {RequestMethod.GET,RequestMethod.DELETE})
    public String deleteClasses(@PathVariable Integer id,Model model){
        classesService.deleteClasses(id);
//        return "classes";
        return findPaginated(1,"classid","asc","",model);
    }

    @GetMapping("/classes/p/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @Param("sortField") String sortField,
                                @Param("sortDir") String sortDir,
                                @Param("keyword") String keyword,
                                Model model){
    int pageSize = 5;
    Page<Classes> page = classesService.findPaginatied(pageNo,pageSize,sortField,sortDir,keyword);
    List<Classes> classes = page.getContent();
    model.addAttribute("currentPage", pageNo);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("totalItems", page.getTotalElements());
    model.addAttribute("classes", classes);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("keyword", keyword);
    String reverserSortDir = sortDir.equals("asc") ? "desc" : "asc";
    model.addAttribute("reverseSortDir", reverserSortDir);
    return "classes";
    }
}
