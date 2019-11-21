package co.edu.icesi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("windowsService")
	private MyProcessService processServiceWindows;
	
	@Autowired
	@Qualifier("linuxService")
	private MyProcessService processServiceLinux;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/windows/")
	public String powershell(Model model) {
		model.addAttribute("allProcesses", processServiceWindows.findAll());
		model.addAttribute("selectedProcess", new MyProcess());
		return "tableWindows";
	}
	
	@GetMapping("/linux/")
	public String bash(Model model) {
		model.addAttribute("allProcesses", processServiceLinux.findAll());
		model.addAttribute("selectedProcess", new MyProcess());
		return "tableLinux";
	}
	
	@PostMapping("/killWindows/")
	public String methodWindows(@ModelAttribute("selectedProcess") MyProcess process, Model model) {
		MyProcess killedProcess = processServiceWindows.findById(process.getId());
		processServiceWindows.killProcess(process.getId());
		model.addAttribute("killedProcess", killedProcess);
		return "killWindows"; 
		
	}
	
	@PostMapping("/killLinux/")
	public String methodLinux(@ModelAttribute("selectedProcess") MyProcess process, Model model) {
		MyProcess killedProcess = processServiceLinux.findById(process.getId());
		processServiceLinux.killProcess(process.getId());
		model.addAttribute("killedProcess", killedProcess);
		return "killLinux"; 
		
	}

}
