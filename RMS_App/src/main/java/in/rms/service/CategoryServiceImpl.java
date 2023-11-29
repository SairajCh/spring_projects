package in.rms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import in.rms.constants.RmsConstants;
import in.rms.entity.Category;
import in.rms.jwt.JwtFilter;
import in.rms.repository.CategoryRepo;
import in.rms.utils.RmsUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService  {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		
		try {
			if(jwtFilter.isAdmin()) {
				if(validateCategoryMap(requestMap,false)) {
					categoryRepo.save(getCategoryFromMap(requestMap,false));
					return RmsUtils.getResponseEntity("Category Added successfully", HttpStatus.OK);
					
				}
				
			}else {
				return RmsUtils.getResponseEntity(RmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	private boolean validateCategoryMap(Map<String,String> requestMap, boolean validateId) {
		
		if(requestMap.containsKey("name")) {
			if(requestMap.containsKey("id") && validateId) {
				return true;
			}else if(!validateId) {
				return true;
			}
			
		}
		return false;
		
	}
	
	private Category getCategoryFromMap(Map<String,String> requestMap, Boolean isAdd) {
		
		Category category = new Category();
		if(isAdd) {
			category.setId(Integer.parseInt(requestMap.get("id")));
		}
		category.setName(requestMap.get("name"));
		return category;
		
	}
	
	
	
	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
	
		try {
			
			
			if(Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
				
				log.info("inside if");
				return new ResponseEntity<List<Category>>(categoryRepo.getAllCategory(),HttpStatus.OK);
			}
			return new ResponseEntity<List<Category>>(categoryRepo.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		
		try {
			if(jwtFilter.isAdmin()) {
				
				if(validateCategoryMap(requestMap, true)) {
					Optional<Category> optional = categoryRepo.findById(Integer.parseInt(requestMap.get("id")));
					if(optional.isEmpty()) {
						categoryRepo.save(getCategoryFromMap(requestMap, true));
						
					}else {
						return RmsUtils.getResponseEntity("Category Id does not exist", HttpStatus.OK);
					}
				}
				
				return RmsUtils.getResponseEntity(RmsConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
				
			}else {
				return RmsUtils.getResponseEntity(RmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
