package com.ashu.services;

import java.util.List;

import com.ashu.binding.DashboardResponse;
import com.ashu.binding.EnqSearchCriteria;
import com.ashu.binding.EnquiryForm;
import com.ashu.entity.StudentEnqEntity;

public interface EnquiryServices {
	
	
	public List<String> loadCourse();
	public List<String> loadEnqStatus();
	public DashboardResponse getdashboard(Integer userId);
	
	public boolean upsertEnquires(EnquiryForm form);    // for inserting and updating enquires
	
	public List<EnquiryForm> getEnquiries(Integer userId, EnqSearchCriteria form);
	
	public List<StudentEnqEntity> getEnquiry();     // for editing form
	
	

}
