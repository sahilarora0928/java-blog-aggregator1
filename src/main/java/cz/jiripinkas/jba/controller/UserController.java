package cz.jiripinkas.jba.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.service.BlogService;
import cz.jiripinkas.jba.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;

	
	@RequestMapping("/account")
	public String account(Model model, Principal principal){
		//principal - this object contains user session and it contains name of the user and if the user is not logged in then it will return null
		 String name = principal.getName();
		 model.addAttribute("user",userService.findOneWithBlogs(name));
		 return "account";
	}

	
	@ModelAttribute("blog") // commandName= "blog" in user-detail is same and it corresponds to this @ModelAttribute("blog")
	public Blog constructBlog(){
		return new Blog();
	}
	
	@RequestMapping(value = "/account", method=RequestMethod.POST)
	public String doAddBlog(Model model,@Valid @ModelAttribute("blog") Blog blog, Principal principal,BindingResult result){
		if(result.hasErrors()){
			return account(model, principal);
		}
		String name = principal.getName();
		blogService.save(blog,name);
		return "redirect:/account.html";//here we dont have to show ?success=true because the new blog will be visible on the page
	}
	
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id){
		Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account.html";
	}
	
	
}
