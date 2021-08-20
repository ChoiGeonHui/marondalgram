package com.marondalgram.timeline.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondalgram.commen.FileManagerSurvice;
import com.marondalgram.timeline.dao.TimelineDAO;

@Service
public class TimelineBO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TimelineDAO timelineDAO;
	
	@Autowired
	FileManagerSurvice fileManagerSurvice;
	
	public void createPost(int userId, String userName,
			String content, MultipartFile file) {
		
		String imagePath = null;
		if(file != null) {
			try {
			imagePath= fileManagerSurvice.saveFile(userName, file);
			} catch (Exception e) {
				log.error("���� ���ε� :"+e.getMessage());
			}	
		}
		
		timelineDAO.insertPost(userId, userName, content, imagePath);
	}

	
}
