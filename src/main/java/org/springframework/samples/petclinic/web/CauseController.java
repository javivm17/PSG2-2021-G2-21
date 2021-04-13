package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/causes")
public class CauseController {

	private final CauseService causeService;
	
	@Autowired
	public CauseController(CauseService causeService) {
		this.causeService = causeService;
	}
	
	@GetMapping()
	public String showCauseList(ModelMap model) {
		Iterable<Cause> causes = causeService.findAll();
		model.put("causes", causes);
		return "causes/causeList";
	}
	
	@GetMapping(path="/new")
	public String initCreateCause(ModelMap modelMap) {
		modelMap.addAttribute("cause", new Cause());
		return "causes/causeNew";
	}
	
	@PostMapping(value = "/save")
	public String processCreationForm(@Valid Cause cause, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("cause", cause);
			return "causes/causeNew";
		}
		else {
			causeService.save(cause);
			return "redirect:/causes";
		}
	}
	
	
}
