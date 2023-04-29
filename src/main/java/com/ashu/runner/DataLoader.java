package com.ashu.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ashu.entity.CourseEntity;
import com.ashu.entity.EnqStatusEntity;
import com.ashu.repo.CourseRepo;
import com.ashu.repo.EnqStatusRepo;
@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo enqRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		courseRepo.deleteAll();
		CourseEntity c1 = new CourseEntity();
		  c1.setCourseName("Java FullStack");
		  
		  CourseEntity c2 = new CourseEntity();
		  c2.setCourseName("DevOps");
		  
		  CourseEntity c3 = new CourseEntity();
		  c3.setCourseName("AWS");
		  
		  courseRepo.saveAll(Arrays.asList(c1,c2,c3));
		  
		  enqRepo.deleteAll();
		  
		  EnqStatusEntity s1 = new EnqStatusEntity();
		  s1.setEnqStatus("New");
		  EnqStatusEntity s2 = new EnqStatusEntity();
		  s2.setEnqStatus("Enrolled");
		  EnqStatusEntity s3 = new EnqStatusEntity();
		  s3.setEnqStatus("Lost");
		  
		  enqRepo.saveAll(Arrays.asList(s1,s2,s3));
		  
		  
		  System.out.println("Data loaded--------------");
		  
		  

	}

}
