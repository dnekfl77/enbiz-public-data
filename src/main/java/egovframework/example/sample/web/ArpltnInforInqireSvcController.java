package egovframework.example.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.sample.service.ArpltnInforInqireSvcService;

@Controller
public class ArpltnInforInqireSvcController {
	@Autowired
	private ArpltnInforInqireSvcService arpltnInforInqireSvcService;

	@RequestMapping(value = "/arpltnInforInqireSvcList.do")
	public String arpltnInforInqireSvcList(Model model) {
		
		model.addAttribute("result", arpltnInforInqireSvcService.getMinuDustFrcstDspth());
		
		return "publicData/arpltnInforInqireSvcList";
	}
}
