package com.koreait.exam;

import com.koreait.exam.vo.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Exam3Controller {
    @Autowired
    private com.koreait.exam.Exam3Service service;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("locationList", service.selLocationCodeList());
        return "main";
    }

    @GetMapping("/result")
    public String result() {
        return "";
    }

    @PostMapping
    public String result(SearchDTO param) {
        service.saveData(param);
        return "redirect:/result";
    }
}
