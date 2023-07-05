package in.ashokit.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.constants.AppConstants;
import in.ashokit.entity.CourseEntity;
import in.ashokit.entity.EnqStatusEntity;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repo.CourseRepo;
import in.ashokit.repo.EnqStatusRepo;
import in.ashokit.repo.StudentEnqRepo;
import in.ashokit.repo.UserDtlsRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;

	@Autowired
	private CourseRepo coursesRepo;

	@Autowired
	private EnqStatusRepo statusRepo;

	@Autowired
	private StudentEnqRepo enqRepo;

	@Autowired
	private HttpSession session;

	@Override
	public DashboardResponse getDashboardData(Integer userId) {
		

		DashboardResponse response = new DashboardResponse();

		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);

		if (findById.isPresent()) {

			UserDtlsEntity userEntity = findById.get();

			List<StudentEnqEntity> enquiries = userEntity.getEnquiries();

			Integer totalCnt = enquiries.size();

			Integer enrolledCnt = enquiries.stream().filter(e -> e.getEnqStatus().equals("ENROLLED"))
					.collect(Collectors.toList()).size();

			Integer lostCnt = enquiries.stream().filter(e -> e.getEnqStatus().equals("LOST"))
					.collect(Collectors.toList()).size();

			response.setEnrolledCnt(enrolledCnt);
			response.setTotalEnquiriesCnt(totalCnt);
			response.setLostCnt(lostCnt);

		}

		return response;
	}

	@Override
	public List<String> getCourses() {
		

		List<CourseEntity> findAll = coursesRepo.findAll();

		List<String> names = new ArrayList();

		for (CourseEntity entity : findAll) {
			names.add(entity.getCourseName());
		}

		return names;
	}

	@Override
	public List<String> getEnqStatuses() {
		

		List<EnqStatusEntity> findAll = statusRepo.findAll();

		List<String> statusList = new ArrayList();

		for (EnqStatusEntity entity : findAll) {
			statusList.add(entity.getStatusName());
		}
		return statusList;
	}

	@Override
	public Boolean saveEnquiry(EnquiryForm form) {
		

		StudentEnqEntity enqEntity = new StudentEnqEntity();
		BeanUtils.copyProperties(form, enqEntity);

		Integer userId = (Integer) session.getAttribute(AppConstants.STR_USER_ID);

		Optional<UserDtlsEntity> userEntity = userDtlsRepo.findById(userId);

		if (userEntity.isPresent()) {
			UserDtlsEntity user = userEntity.get();
			enqEntity.setUser(user);
		}

		enqRepo.save(enqEntity);

		return true;
	}

	@Override
	public List<StudentEnqEntity> getEnquiries() {

		

		Integer userId = (Integer) session.getAttribute(AppConstants.STR_USER_ID);

		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);

		if (findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();

			return userDtlsEntity.getEnquiries();

		}

		return Collections.emptyList();
	}

	@Override
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {
		

		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);

		if (findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();

			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();

			// filter logic

			if (null != criteria.getCourseName() && !"".equals(criteria.getCourseName())) {
				enquiries = enquiries.stream().filter(e -> e.getCourseName().equals(criteria.getCourseName()))
						.collect(Collectors.toList());

			}
			if (null != criteria.getClassMode() && !"".equals(criteria.getClassMode())) {
				enquiries = enquiries.stream().filter(e -> e.getClassMode().equals(criteria.getClassMode()))
						.collect(Collectors.toList());

			}
			if (null != criteria.getEnquiryStatus() && !"".equals(criteria.getEnquiryStatus())) {
				enquiries = enquiries.stream().filter(e -> e.getEnqStatus().equals(criteria.getEnquiryStatus()))
						.collect(Collectors.toList());
			}

			return enquiries;

		}

		return Collections.emptyList();
	}

	@Override
	public EnquiryForm getStudentEnqForm(Integer enqId) {

		Optional<StudentEnqEntity> findById = enqRepo.findById(enqId);

		EnquiryForm enqForm = new EnquiryForm();

		if (findById.isPresent()) {
			StudentEnqEntity studentEnqEntity = findById.get();

			enqForm.setClassMode(studentEnqEntity.getClassMode());
			enqForm.setCourseName(studentEnqEntity.getCourseName());
			enqForm.setEnqStatus(studentEnqEntity.getEnqStatus());
			enqForm.setStudentName(studentEnqEntity.getStudentName());
			enqForm.setStudentPhno(studentEnqEntity.getStudentPhno());

			return enqForm;
		}
		return null;

	}

}
