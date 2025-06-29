/*
SPDX-FileCopyrightText: Copyright (c) 2022-2023 Andrea Binello ("andbin")
SPDX-License-Identifier: MIT
*/

package ua.se.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.se.sample.service.AppInfoService;

@Controller
public class HomeController {
    @Autowired
    private AppInfoService appInfoService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("appInfo", appInfoService.getAppInfo());
        return "home";   // refers to classpath resource /templates/home.html
    }
}
