package org.springframework.samples.sieteislas.statistics.achievement;

import java.security.Principal;
import java.util.Collection;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistics/achievements")
public class AchievementController {
	
    private String CREATE_OR_UPDATE_ACHIEVEMENT_FORM = "/achievements/AchievementCreateOrUpdateForm";
    private String ACHIEVEMENTS_LISTING = "/achievements/AchievementsListing";
    
    private final AchievementService achievementService;
    private final UserService userService;
    
    @Autowired
    public AchievementController(AchievementService achievementService,
    		UserService userService) {
        
    	this.achievementService = achievementService;
        this.userService = userService;
    }
    
    @GetMapping("/")
    public String getAllAchievements(Map<String, Object> model,
    		Principal principal) {
    	
        Collection<Achievement> achievements = achievementService.getAllAchievements();
        Boolean isAdmin = userService.isAdmin(principal.getName());
        
        model.put("achievements", achievements);
        model.put("isAdmin", isAdmin);
        
        return ACHIEVEMENTS_LISTING;
    }
    
    @GetMapping("/delete/{achievementId}")
    public String deleteAchievement(@PathVariable("achievementId") int id){
        
    	achievementService.deleteAchievementById(id);
       
    	return "redirect:/statistics/achievements/";
    }
    
    @GetMapping("/edit/{id}")
    public String initUpdateForm(@PathVariable("id") int id, Map<String, Object> model) {
       
    	Achievement achievement = this.achievementService.getAchievementById(id);
        model.put("achievement", achievement);
        
        return CREATE_OR_UPDATE_ACHIEVEMENT_FORM;
    }
    
    @PostMapping("/edit/{id}")
    public String processUpdateForm(@Valid Achievement achievement, BindingResult result,
                            @PathVariable("id") int id, Map<String, Object> model){
        
    	if(result.hasErrors()){
        	
            model.put("achievement", achievement);
            return CREATE_OR_UPDATE_ACHIEVEMENT_FORM;
            
        } else{
        	
            Achievement achievement2Update = this.achievementService.getAchievementById(id);
            BeanUtils.copyProperties(achievement, achievement2Update, "id");
            
            achievementService.saveAchievement(achievement2Update);
        }
        return "redirect:/statistics/achievements/";
    }
    
	public ModelAndView showAll(){
		
		ModelAndView res = new ModelAndView(ACHIEVEMENTS_LISTING);
		res.addObject("achievements", achievementService.getAllAchievements());
		
		return res;
	}
    
	@GetMapping(value = "/new")
	public String create(Map<String, Object> model) {
		
		Achievement a = new Achievement();
        model.put("achievement", a);
        
        return CREATE_OR_UPDATE_ACHIEVEMENT_FORM;
	}
	
	@PostMapping(value = "/new")
	public String post(@Valid Achievement a, Map<String, Object> model,
			BindingResult result) {
		
		if(result.hasErrors()){
			
            model.put("achievement", a);
            return CREATE_OR_UPDATE_ACHIEVEMENT_FORM;
            
        } else
        	achievementService.saveAchievement(a);
        
        return "redirect:/statistics/achievements/";
	}
	
}