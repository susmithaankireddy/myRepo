package com.demo.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.jpa.EmpJpaRepository;

@RestController
@RequestMapping("/emp")
@CrossOrigin()
public class EmpRestController {

	@Autowired
	EmpJpaRepository emprepo;
	
	@GetMapping(path="/list",produces="application/json")
	public ResponseEntity<List<Emp>> getEmpList(){
		return ResponseEntity.ok(emprepo.findAll());
	}

	
	
	/*@GetMapping(path="/find/{id}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity findEmp(@PathVariable("id") int id) {
		
		Optional<Emp> o = emprepo.findById(id);
		if(o.isPresent()) {
			throw new EmpClassNotFound();
		}
		return ResponseEntity.status(404).build();
	}*/
	
	@RequestMapping(path="/find/{id}",method = RequestMethod.GET)
	public Optional<Emp> find(@PathVariable("id") int id) {
		 Optional<Emp> e = emprepo.findById(id);
		
		return e;
	}
	
	
	@PostMapping(path="/save",produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> saveEmp(@RequestBody Emp e) {
		if(emprepo.existsById(e.getId())) {
			return ResponseEntity.ok("Employee exits with id"+e.getId());
		}
		emprepo.save(e);
		return ResponseEntity.ok("Employee saved with id"+e.getId());
	}
	
	@ExceptionHandler(EmpClassNotFound.class)
	public ModelAndView exception(EmpClassNotFound e) {
		ModelAndView m = new ModelAndView();
		m.setViewName("errorPage");
		return m;
	}
}
