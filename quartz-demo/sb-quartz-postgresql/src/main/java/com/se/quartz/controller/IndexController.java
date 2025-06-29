package com.se.quartz.controller;


import com.se.quartz.entity.Task;
import com.se.quartz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private TaskService taskService;


	@GetMapping("/index")
	public String index(Model model){
		List<Task> list = taskService.list();
		model.addAttribute("jobs", list);
		return "index";
	}
}
