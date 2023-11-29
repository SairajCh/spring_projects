package in.ies.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ies.bindings.PlanForm;
import in.ies.entities.PlanEntity;
import in.ies.repositories.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanRepo planRepo;

	@Override
	public boolean createPlan(PlanForm planForm) {
		
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(planForm, planEntity);
		
		
		
		
		
		
		return false;
	}

	@Override
	public List<PlanForm> fetchPlans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanForm getPlanById(Integer planId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePlanStatus(Integer planId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
