package com.demo.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.jpa.CustJpaRepository;
import com.demo.spring.jpa.EmpJpaRepository;
import com.demo.spring.jpa.SuppJpaRepository;

@RestController
public class EmpController {
	
	
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	Map<Integer, Employee> map_emp = new HashMap();
	
	
	@RequestMapping(value="/greeting",method = RequestMethod.GET)
	public @ResponseBody Greeting greeting(@RequestParam(value = "name",defaultValue = "World!") String name) {
		return new Greeting(counter.incrementAndGet(),String.format(template, name));
	}
	
	@RequestMapping(path=EmpRestURIConstants.DUMMY_EMP,method = RequestMethod.GET)
	public @ResponseBody Employee getDummyEmp(){
	
	Employee e = new Employee(counter.incrementAndGet(), "susmitha", new Date());
	map_emp.put(counter.intValue(), e);
	return e;
	}
	
	@RequestMapping(value = EmpRestURIConstants.GET_ALL_EMP,method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployees() {
		List<Employee> list = new ArrayList<Employee>();
		Set<Integer> empIds = map_emp.keySet();
		for(Integer i:empIds) {
			list.add(map_emp.get(i));
		}
		return list;
	}
	
	
	@Autowired
	EmpJpaRepository repo;
	
	@Autowired
	CustJpaRepository cstrepo;
	@Autowired
	SuppJpaRepository suprepo;
	
	
	SuppRequest sp;
	
	
	@PostMapping("/greet/{name}")
	public String greetwrgqu(@PathVariable("name") String name) {
		return "hello ywrc23yuteu jhrfwer from  rest"+name;
	}
	
	
	
	@GetMapping("/index")
	public String loadIndexPage() {
		return "index";
	}
	
	@GetMapping("/list")
	public ModelAndView listAll() {
		ModelAndView m = new ModelAndView();
		
		List<Emp> list = repo.findAll();
		m.addObject("list", list);
		m.setViewName("list");
		return m;
	}
	
	
	
	@RequestMapping(path="/find",method = RequestMethod.POST)
	public ModelAndView find(@RequestParam("id") int id) {
		ModelAndView m = new ModelAndView();
	    Optional<Emp> e = repo.findById(id);
		if(e.isPresent()) {
			m.addObject("result", e.get());
			
			m.setViewName("empDetails");
		}	
		else {
			m.setViewName("errorPage");
		}
		
		return m;
	}
	
	
	
	
	@Autowired
	IndexValidator validator;
	
	@Autowired
	CustomerValidator cstvalidator;
	
	@GetMapping("/check")
	public  ModelAndView start(@ModelAttribute("suppRequest") SuppRequest s) {
		ModelAndView m = new ModelAndView();
		m.setViewName("index");
		m.addObject("suppRequest",s);
		return m;
	}
	

	
	@PostMapping("/change")
	public @ResponseBody ModelAndView editCust(@ModelAttribute("customer") Customer c) {
		System.out.println("email :"+c.getEmail());
		cstrepo.save(c);
		ModelAndView m = new ModelAndView();
		m.addObject("suppRequest", new SuppRequest());
		m.setViewName("index");
		return m;
	}

	@GetMapping("/update")
	public ModelAndView getAll() {
		ModelAndView m = new ModelAndView();
		List<Customer> l = cstrepo.findAll();
		m.addObject("list", l);
		m.setViewName("list");
		return m;
	}
	
	@GetMapping("/delete")
	public ModelAndView getAllDelete() {
		ModelAndView m = new ModelAndView();
		List<Customer> l = cstrepo.findAll();
		m.addObject("list", l);
		m.setViewName("listDelete");
		return m;
	}
	@PostMapping("/edit")
	public @ResponseBody ModelAndView editCust(@RequestParam String email) {
		ModelAndView m = new ModelAndView();
		for(Customer c: cstrepo.findAll()) {
		    if(c.getEmail().equals(email)) {
		    	m.addObject("cust",c);
		    }
		}
		m.setViewName("update");
		return m;
	}
	@PostMapping("/deleteCust")
	public @ResponseBody ModelAndView delete(@RequestParam String email) {
		System.out.println("email: "+email);
		ModelAndView m = new ModelAndView();
		List<SuppRequest> l = suprepo.findAll();
		for(SuppRequest r:l) {
			if(r.getEmail().equals(email)) {
				suprepo.delete(r);
			}
			
		}
		for(Customer c: cstrepo.findAll()) {
		    if(c.getEmail().equals(email)) {
		    	cstrepo.delete(c);
		    }
		}
		
		m.addObject("list", cstrepo.findAll());
		m.setViewName("list");
		return m;
	}
	
	
	@PostMapping("/check")
	public ModelAndView checkCustomer(@ModelAttribute("suppRequest") SuppRequest suppRequest,BindingResult errors) {
		ModelAndView m = new ModelAndView();
		validator.validate(suppRequest, errors);
		if (errors.hasErrors()) {
			m.setViewName("index");
			return m;
		}
		System.out.println("enteringgggg");
		boolean status = false;
		String name = "";
		Iterable<Customer> list = cstrepo.findAll();
		
		for(Customer customer:list) {
			if(suppRequest.getEmail().equals(customer.getEmail())) {
				status = true;
				name = customer.getFirstname()+customer.getLastname();
			}
		}
		if(status) {
			suprepo.save(suppRequest);
			m.setViewName("response");
			m.addObject("name",name );
		}
		else {
			sp = suppRequest;
			m.addObject("customer", new Customer());
			m.setViewName("registration");
			
		}
		return m;
	}
	
	/*@RequestMapping(path="/register",method=RequestMethod.GET)
	public String processForm() {
		return "emp";
	}*/
	
	
	@PostMapping("/register")
	public ModelAndView processForm(@ModelAttribute("customer") Customer e,BindingResult errors) {
		
		ModelAndView m = new ModelAndView();
		cstvalidator.validate(e, errors);
		if (errors.hasErrors()) {
			m.setViewName("registration");
			return m;
		}
		
		e.setEmail(sp.getEmail());
		cstrepo.save(e);
		suprepo.save(sp);
		m.setViewName("response");
		m.addObject("name", e.getFirstname()+e.getLastname());
		return m;
	}
	

	
}
