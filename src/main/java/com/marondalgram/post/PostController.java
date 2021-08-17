package com.marondalgram.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@RequestMapping("/post_list_view")
	public String postlist(Model model) {
		model.addAttribute("marondal", "/post/listview");
		return "templete/layout";
	}

}
