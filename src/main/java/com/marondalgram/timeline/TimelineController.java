package com.marondalgram.timeline;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.marondalgram.timeline.bo.ContentBO;
import com.marondalgram.timeline.bo.TimelineBO;
import com.marondalgram.timeline.domain.Content;

@Controller
public class TimelineController {
	
	
	@Autowired
	TimelineBO timelineBO;
	
	@Autowired
	ContentBO contentBO;
	
	@RequestMapping("/timeline")
	public String timeline(Model model,
			HttpServletRequest request) {
		
		Integer userId = null;
		String userName = null;
		
		HttpSession session = request.getSession();
		userId =(Integer) session.getAttribute("userId");
		userName = (String) session.getAttribute("userName");
		//List<Post> list = timelineBO.getListPostById(userId);
		List<Content> list = contentBO.AllPostListView();
		
		
		model.addAttribute("userId", userId);
		model.addAttribute("UserName", userName);
		model.addAttribute("contentList", list);
		model.addAttribute("marondal", "timeline/timeline");
		return "/templete/layout";
	}

}
