package com.bjit.main.controller;

import com.bjit.main.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    List<Student> studentList = new ArrayList<>();
    Student defaultStudent1 = new Student("102", "B", "Java");
    Student defaultStudent2 = new Student("103", "C", "Java");
    boolean isDefaultStudentAdded = false;



    @GetMapping("/registraion")
    public ModelAndView registraionPage(ModelAndView model) {
        model.setViewName("RegistrationFIeld.html");
        return model;
    }

    @PostMapping("/studentAdded")
    public ModelAndView receiveData(@ModelAttribute Student student, ModelAndView model) {
        model.setViewName("AddedStudentInfo.html");
        model.addObject("candidate", student);
        Student student1 = new Student(student.getId(), student.getName(), student.getDomain());
        studentList.add(student1);
        return model;
    }

    @GetMapping("allCandidates")
    public ModelAndView getAllCandidates(ModelAndView model) {

        if(isDefaultStudentAdded==false){
            studentList.add(defaultStudent1);
            studentList.add(defaultStudent2);
            isDefaultStudentAdded = true;
        }
        model.setViewName("AllStudentPage.html");
        model.addObject("candidateList", studentList);
        return model;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteField(ModelAndView model) {
        model.setViewName("studentDeleteField.html");
        return model;
    }

    @PostMapping("deletedStudentById")
    public ModelAndView postDeletedStudentId(@RequestParam String studentId,ModelAndView model) {
        model.setViewName("DeleteMessage.html");
        model.addObject("studentId", studentId);
        int indexToDelete = -1;
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getId().equals(studentId)) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete != -1) {
            studentList.remove(indexToDelete);
        }
        return model;
    }



}
