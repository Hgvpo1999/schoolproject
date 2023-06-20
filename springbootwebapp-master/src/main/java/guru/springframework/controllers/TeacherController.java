package guru.springframework.controllers;

import guru.springframework.domain.Classes;
import guru.springframework.domain.Teacher;
import guru.springframework.domain.TeacherClass;
import guru.springframework.services.ClassesService;
import guru.springframework.services.TeacherClassService;
import guru.springframework.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@Controller
public class TeacherController {
    private TeacherService teacherService;
    @Autowired
    public void setTeacherService(TeacherService teacherService){
        this.teacherService =teacherService;
    }
    private TeacherClassService teacherClassService;
    @Autowired
    public void setTeacherClassService(TeacherClassService teacherClassService){
        this.teacherClassService =teacherClassService;
    }
    private ClassesService classesService;
    @Autowired
    public void setClassService(ClassesService classesService){
        this.classesService = classesService;
    }
    @GetMapping(value = "/teachers")
    public String list(Model model,Principal principal){
        String keyword ="";
        return findPaginated(1,"teacherid","asc",keyword,"",null,model,principal);
    }
    @GetMapping(value = "/teacher/{id}")
    public String showTeacher(@PathVariable Integer id, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teachershow";
    }
    @RequestMapping(value = "teacher/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teacherform";
    }
    @RequestMapping(value = "teacher/new",method = RequestMethod.GET)
    public String newTeacher(Model model){
        model.addAttribute("teacher", new Teacher());
        return "teacherform";
    }
    @RequestMapping(value = "teacher", method = RequestMethod.POST)
    public String saveTeacher(Teacher teacher,Model model){
        teacherService.saveTeacher(teacher);
        return "redirect:/teacher/" + teacher.getTeacherid();
    }
    @RequestMapping(value = "teacher/delete/{id}",method = {RequestMethod.GET,RequestMethod.DELETE})
    public String deleteTeacher(@PathVariable Integer id,
                                Model model,Principal principal){
        teacherService.deleteTeacher(id);
        return findPaginated(1,"teacherid","asc","","",null,model,principal);
    }
    @GetMapping(value = "/teacher/class/{id}")
    public String viewClass(@PathVariable Integer id, Model model){
        List<Classes> classes = teacherClassService.viewClass(id);
        model.addAttribute("teachers",teacherService.getTeacherById(id));
        model.addAttribute("classes",classes);
        return "viewClass";
    }
    @RequestMapping(value = "teacher/class/{teacherid}/{classid}", method = {RequestMethod.GET,RequestMethod.DELETE})
    public String removeClass(@PathVariable Integer teacherid,
                              @PathVariable Integer classid,
                              Model model){
        model.addAttribute("teachers",teacherService.getTeacherById(teacherid));
        List<Classes> classes = teacherClassService.viewClass(teacherid);
        model.addAttribute("classes", classes);
        teacherClassService.deleteClass(teacherid,classid);
        return "redirect:/teacher/class/{teacherid}";
    }
    @GetMapping("/teacher/p/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @Param("sortField") String sortField,
                                @Param("sortDir") String sortDir,
                                @Param("keyword") String keyword,
                                @Param("subject") String subject,
                                @Param("homeroom") Integer homeroom,
                                Model model, Principal principal){
        int pageSize = 5;
        Page<Teacher> page = teacherService.findPaginatied(pageNo,pageSize,sortField,sortDir,keyword,subject,homeroom);
        List<Teacher> teachers = page.getContent();
        List<Classes> classes = classesService.listAllClass();
        model.addAttribute("principal",principal);
        model.addAttribute("classes", classes);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("teachers", teachers);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("subject", subject);
        model.addAttribute("homeroom", homeroom);
        String reverserSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverserSortDir);
        return "teachers";
    }

    @GetMapping(value = "teacher/class/add/{id}")
    public String viewClassNotTeach(@PathVariable Integer id,Model model){
        List<Classes> classes = teacherService.viewClassNotTeach(id);
        model.addAttribute("classes",classes);
        model.addAttribute("teachers", teacherService.getTeacherById(id));
        return "classnotteach";
    }
    @RequestMapping(value = "teacher/class/add/{teacherid}/{classid}",method = {RequestMethod.GET,RequestMethod.POST})
    public String addClassNotTeach(@PathVariable Integer teacherid,
                                   @PathVariable Integer classid,
                                   Model model){
        model.addAttribute("teachers",teacherService.getTeacherById(teacherid));
        List<Classes> classes = teacherService.viewClassNotTeach(teacherid);
        model.addAttribute("classes", classes);
        teacherService.addClassNotTeach(teacherid,classid);
        return "redirect:/teacher/class/add/{teacherid}";
    }

}
