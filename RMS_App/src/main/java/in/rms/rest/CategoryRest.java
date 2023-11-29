package in.rms.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.rms.constants.RmsConstants;
import in.rms.entity.Category;
import in.rms.service.CategoryService;
import in.rms.utils.RmsUtils;

@RestController
public class CategoryRest {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/add")
	ResponseEntity<String> addNewCategory(@RequestBody Map<String,String> requestMap){
		try {
			
			return categoryService.addNewCategory(requestMap);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		
	}
	
	@GetMapping("/get")
	ResponseEntity<List<Category>> getAllCategory(@RequestParam(required=false)String filterValue){
		
		try {
			return categoryService.getAllCategory(filterValue);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
		
	@PostMapping("/update")
	ResponseEntity<String> updateCategory(@RequestBody Map<String,String> requestMap){
		
		try {
			
			return categoryService.updateCategory(requestMap);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	
	

}
