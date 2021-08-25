package com.marondalgram.timeline;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marondalgram.post.model.Post;
import com.marondalgram.timeline.bo.TimelineBO;

@Controller
public class TimelineController {
	
	
	@Autowired
	TimelineBO timelineBO;
	
	@RequestMapping("/timeline")
	public String timeline(Model model,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId =(int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		List<Post> list = timelineBO.getListPostById(userId);
		
		
		model.addAttribute("userName", userName);
		model.addAttribute("postlist", list);	
		model.addAttribute("marondal", "timeline/timeline");
		return "/templete/layout";
	}

}
