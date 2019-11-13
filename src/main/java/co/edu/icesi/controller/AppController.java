package co.edu.icesi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import co.edu.icesi.model.MyProcess;
import co.edu.icesi.service.MyProcessService;

@Controller
public class AppController {
	
	@Autowired
	private MyProcessService processService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/windows/")
	public String powershell(Model model) {
		model.addAttribute("allProcesses", processService.findAll());
		model.addAttribute("selectedProcess", new MyProcess());
		return "table";
	}
	
	@PostMapping("/kill/")
	public String method(@ModelAttribute("selectedProcess") MyProcess process, Model model) {
		MyProcess killedProcess = processService.findById(process.getId());
		processService.killProcess(process.getId());
		model.addAttribute("killedProcess", killedProcess);
		return "kill"; 
		
	}
	

}
