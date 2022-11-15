package org.springframework.samples.sieteislas.statistics.achievement;

import java.util.Collection;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    //GET ALL
    @GetMapping("/")
    public String getAllAchievements(Map<String, Object> model) {
        Collection<Achievement> achievements = achievementService.getAllAchievements();
        model.put("achievements", achievements);
        return ACHIEVEMENTS_LISTING;
    }
    //DELETE
    @GetMapping("/delete/{achievementId}")
    public String deleteAchievement(@PathVariable("achievementId") int id){
        achievementService.deleteAchievementById(id);
        return "redirect:/statistics/achievements/"; //Redirect to GET ALL url. (which provides de achievement listing view)
    }

    //UPDATE (2 STEPS)
    //UPDATE (1/2; Retrieve the FORM)
    @GetMapping("/edit/{id}")
    public String initUpdateForm(@PathVariable("id") int id, Map<String, Object> model) {
        Achievement achievement = this.achievementService.getAchievementById(id);
        model.put("achievement", achievement);
        return CREATE_OR_UPDATE_ACHIEVEMENT_FORM;
    }

    //UPDATE (2/2; After the user fills it, we POST the form)
    @PostMapping("/edit/{id}")                                  //result stores the possible errors
    public String processUpdateForm(@Valid Achievement achievement, BindingResult result,
                            @PathVariable("id") int id, Map<String, Object> model){
        if(result.hasErrors()){
            model.put("achievement", achievement);
            return CREATE_OR_UPDATE_ACHIEVEMENT_FORM;
        } else{
            Achievement achievement2Update = this.achievementService.getAchievementById(id);
            BeanUtils.copyProperties(achievement, achievement2Update, "id"); //demas argumentos props que se ignoran.
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
	public ModelAndView create() {
		
		Achievement a = new Achievement();
        ModelAndView res = new ModelAndView(CREATE_OR_UPDATE_ACHIEVEMENT_FORM);
        res.addObject("achievement", a);
        
        return res;
	}
	
	@PostMapping(value = "/new")
	public ModelAndView post(Achievement a) {
		
		achievementService.saveAchievement(a);
		
        ModelAndView res = showAll();
        res.addObject("message", "The achievement was created successfully");
        
        return res;
	}
}
