package com.dbdesign.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbdesign.dao.ContactDao;
import com.dbdesign.model.Contact;



@Controller
public class HomeController {
	
	@Autowired
    private ContactDao contactDAO;
	
	  //Initial page redirect
	  @RequestMapping(value = "/", method = RequestMethod.GET) 
	  public ModelAndView listContact(ModelAndView  model) throws IOException, SQLException
	  { 
	  List<Contact> listContact = contactDAO.list();
	  model.addObject("listContact", listContact);
	  model.setViewName("home");
	  return model; 
	  }
	  
	  //Insert records
	  @RequestMapping(value = "/insertContact", method = RequestMethod.POST)
	  public ModelAndView insertContact(@ModelAttribute("userForm") Contact contact) throws
	  IOException, SQLException { 
		  contactDAO.insert(contact); 
		  ModelAndView model = new ModelAndView();
		  model.setViewName("home"); 
		  return model; 
		  }
	  
	  //Delete records
	  @RequestMapping(value = "/deleteContact/{id}", method = RequestMethod.POST) 
	  public ModelAndView deleteContact(@PathVariable("id") int id) throws IOException, SQLException
	  { 
	  int contactId = contactDAO.delete(id);
	  ModelAndView model = new ModelAndView();
	  model.addObject("contactId", contactId);
	  model.setViewName("delete");
	  return model;
	  }
	  
	  //Search page redirect
	  @RequestMapping(value = "/searchPage", method = RequestMethod.GET) public
	  ModelAndView searchPage() throws IOException, SQLException { ModelAndView
	  model = new ModelAndView(); model.setViewName("search"); return model; }
	  
	  //Search string
	  @RequestMapping(value = "/search/{str}", method = RequestMethod.POST) public
	  ModelAndView search(@PathVariable("str") String str) throws IOException, SQLException {
		  ModelAndView model = new ModelAndView();
		  List<Contact> list = contactDAO.search(str);
		  model.addObject("contacts",list);
		  model.setViewName("search"); 
	 return model; 
	 }
	  
	  //Redirect to edit page
	  @RequestMapping(value = "/editPage/{id}", method = RequestMethod.POST) public
	  ModelAndView editPage(@PathVariable("id") int id) throws IOException, SQLException { 
		  Contact contact = contactDAO.edit(id);
		  ModelAndView  model = new ModelAndView(); 
		  model.addObject("contact", contact);
		  model.addObject("test", 1);
		  model.setViewName("edit");
		  return model; }
	  
	  //Update record
	  @RequestMapping(value = "/edit", method = RequestMethod.POST) 
	  public ModelAndView edit(@ModelAttribute("userForm") Contact contact) throws IOException, SQLException { 
		  System.out.println(contact.getFname());
		  contactDAO.update(contact);
		  ModelAndView  model = new ModelAndView(); 
		  model.setViewName("home");
		  return model; 
		  }
}
