package in.ies.service;

import java.util.List;

import in.ies.binding.App;

public interface ArService {


	public String createApplication(App app);
	
	public List<App> fetchApps(Integer userId);


}
