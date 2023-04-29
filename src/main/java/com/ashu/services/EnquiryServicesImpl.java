package com.ashu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashu.binding.DashboardResponse;
import com.ashu.binding.EnqSearchCriteria;
import com.ashu.binding.EnquiryForm;
import com.ashu.entity.CourseEntity;
import com.ashu.entity.EnqStatusEntity;
import com.ashu.entity.StudentEnqEntity;
import com.ashu.entity.UserDtlsEntity;
import com.ashu.repo.CourseRepo;
import com.ashu.repo.EnqStatusRepo;
import com.ashu.repo.StudentEnqRepo;
import com.ashu.repo.UserDtlsRepo;

@Service
public class EnquiryServicesImpl implements EnquiryServices {

	@Autowired
	private UserDtlsRepo userRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private EnqStatusRepo enquiryRepo;

	@Autowired
	private StudentEnqRepo studentRepo;

	@Autowired
	private HttpSession session;

	@Override
	public List<String> loadCourse() {

		List<CourseEntity> findAll = courseRepo.findAll();
		List<String> name = new ArrayList<>();
		for (CourseEntity courName : findAll) {
			name.add(courName.getCourseName());
		}

		return name;
	}

	@Override
	public List<String> loadEnqStatus() {

		List<EnqStatusEntity> findAll = enquiryRepo.findAll();
		List<String> statusList = new ArrayList<>();

		for (EnqStatusEntity enquiry : findAll) {
			statusList.add(enquiry.getEnqStatus());
		}

		return statusList;
	}

	@Override
	public DashboardResponse getdashboard(Integer userId) {

		DashboardResponse dashBoardResponse = new DashboardResponse();
		Optional<UserDtlsEntity> findById = userRepo.findById(userId);

		if (findById.isPresent()) {

			UserDtlsEntity userEntity = findById.get();
			List<StudentEnqEntity> enquiries = userEntity.getEnquries();
			Integer totalEnquries = enquiries.size();

			Integer totalEnrolled = enquiries.stream().filter(e -> e.getEnqStatus().equals("Enrolled"))
					.collect(Collectors.toList()).size();

			Integer totalLost = enquiries.stream().filter(e -> e.getEnqStatus().equals("Lost"))
					.collect(Collectors.toList()).size();

			dashBoardResponse.setTotalEnquiries(totalEnquries);
			dashBoardResponse.setTotalEnrolled(totalEnrolled);
			dashBoardResponse.setTotalLost(totalLost);

		}
		return dashBoardResponse;

	}

	@Override
	public boolean upsertEnquires(EnquiryForm form) {

		StudentEnqEntity student = new StudentEnqEntity();
		BeanUtils.copyProperties(form, student);

		Integer userId = (Integer) session.getAttribute("userId");

		UserDtlsEntity userEntity = userRepo.findById(userId).get();
		student.setUser(userEntity);
		studentRepo.save(student);

		return true;
	}

	@Override
	public List<EnquiryForm> getEnquiries(Integer userId, EnqSearchCriteria form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEnqEntity> getEnquiry() {

		Integer userId = (Integer) session.getAttribute("userId");
		Optional<UserDtlsEntity> findById = userRepo.findById(userId);
		if (findById.isPresent()) {

			UserDtlsEntity userEntity = findById.get();
			
			List<StudentEnqEntity> studentList = userEntity.getEnquries();
              return studentList;
		}



		return null;
	}

}
