package cz.jiripinkas.jba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id){
		model.addAttribute("user",userService.findOneWithBlogs(id));
		return "user-detail";
	}
	
	
	@ModelAttribute("user")// this @modelAttribute("user")user will be mapped to form commandName="user" , thats how it will bound to an object from spring controller to the jsp file 
	public User construct(){
		return new User();
	}
	
	@RequestMapping("/register")
	public String showRegistrer(){
		return "user-register";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String doRegistrer(@ModelAttribute("user") User user){
		userService.save(user);
		return "user-register";
	}
}
