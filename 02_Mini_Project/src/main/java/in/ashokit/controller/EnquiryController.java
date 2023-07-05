package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.constants.AppConstants;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.service.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqService;

	@Autowired
	private HttpSession session;

	@GetMapping("/logout")
	public String logout() {

		session.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {

		Integer userId = (Integer) session.getAttribute("userId");

		DashboardResponse dashboardData = enqService.getDashboardData(userId);

		model.addAttribute("dashboardData", dashboardData);

		return "dashboard";
	}

	@GetMapping("/enquiry")
	public String addenquiryPage(Model model) {

		// get courses for drop down
		List<String> courses = enqService.getCourses();

		// get enq Status for dropdown

		List<String> enqStatuses = enqService.getEnqStatuses();

		// create binding class obj
		EnquiryForm formObj = new EnquiryForm();

		// set data in model object
		model.addAttribute(AppConstants.STR_COURSE_NAMES, courses);
		model.addAttribute("enqStatusNames", enqStatuses);
		model.addAttribute(AppConstants.STR_FORM_OBJECT, formObj);

		return AppConstants.STR_ADD_ENQUIRY;
	}

	@PostMapping("/enquiry")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {

		

		Boolean status = enqService.saveEnquiry(formObj);

		if (status) {
			model.addAttribute("succMsg", "Enquiry Added");
		} else {
			model.addAttribute("errMsg", "Problem Occured");
		}

		return AppConstants.STR_ADD_ENQUIRY;

	}

	private void initForm(Model model) {
		// get enq statsuses for drop down
		List<String> enqStatuses = enqService.getEnqStatuses();

		// get courses for drop down
		List<String> courses = enqService.getCourses();

		// create binding class obj
		EnquiryForm formObj = new EnquiryForm();

		model.addAttribute(AppConstants.STR_COURSE_NAMES, courses);
		model.addAttribute("statusNames", enqStatuses);
		model.addAttribute(AppConstants.STR_FORM_OBJECT, formObj);

	}

	@GetMapping("/enquiries")
	public String viewEnquiriesPage(EnquirySearchCriteria criteria, Model model) {

		initForm(model);
		
		List<StudentEnqEntity> enquiries = enqService.getEnquiries();

		model.addAttribute("enquiries", enquiries);

		return "view-enquiries";
	}

	@GetMapping("/filter-enquiries")
	public String getFilteredEnqs(@RequestParam String cname, @RequestParam String status, @RequestParam String mode,Model model) {

		EnquirySearchCriteria criteria = new EnquirySearchCriteria();
		criteria.setClassMode(mode);
		criteria.setCourseName(cname);
		criteria.setEnquiryStatus(status);
		Integer userId = (Integer) session.getAttribute(AppConstants.STR_USER_ID);

		List<StudentEnqEntity> filteredEnqs = enqService.getFilteredEnqs(criteria, userId);
		
		
		model.addAttribute("enquiries",filteredEnqs);

		return "filter-enquiries-page";
	}
	
	
	@GetMapping("/edit")
	public String edit(@RequestParam("enqId") Integer enqId,Model model) {
		
		
		List<String> enqStatuses = enqService.getEnqStatuses();

		// get courses for drop down
		List<String> courses = enqService.getCourses();
		
		EnquiryForm enqForm = enqService.getStudentEnqForm(enqId);	
		model.addAttribute(AppConstants.STR_FORM_OBJECT, enqForm);
		
		model.addAttribute(AppConstants.STR_COURSE_NAMES, courses);
		model.addAttribute("enqStatusNames", enqStatuses);
		model.addAttribute("enqId", enqId);
		
		
		return AppConstants.STR_ADD_ENQUIRY;
	}

}
