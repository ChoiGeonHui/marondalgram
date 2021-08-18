package com.marondalgram.timeline;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimelineController {
	
	@RequestMapping("/timeline")
	public String timeline(Model model) {
		model.addAttribute("marondal", "timeline/timeline");
		return "/templete/layout";
	}

}
