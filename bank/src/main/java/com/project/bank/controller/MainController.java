package com.project.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {



    @GetMapping(value={"/","/main"})
    public String MainPage(Model model){






        return "Main/MainPage";
    }

    @PostMapping(value={"/","/main"})
    public String MainAccount(Model model){






        return "Main/MainPage";
    }

}
