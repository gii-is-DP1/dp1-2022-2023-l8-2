/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.sieteislas.user;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller

public class UserController {

	private static final String VIEWS_USER_CREATE_FORM = "users/createUserForm";
	private static final String VIEWS_USER_PROFILE = "users/userProfile";
	private static final String VIEWS_UPDATE_USER_PROFILE = "users/userProfileEditForm";
	
	private final UserService userService;
	private final PlayerService playerService;

	@Autowired
	public UserController(PlayerService playerService, UserService userService) {
		this.playerService = playerService;
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("users", user);
		return VIEWS_USER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_FORM;
		}
		else {
			//creating owner, user, and authority
			
			user.setEnabled(true);
			
			Player player = new Player();
			
			player.setUser(user);
			user.setPlayer(player);
			
			Authorities auth = new Authorities();
			auth.setUser(user);
			auth.setAuthority("player");
			
			user.setAuthorities(Set.of(auth));
			
			this.userService.saveUser(user);
			
			return "redirect:/";
		}
	}

	@GetMapping("/users/home/profile")
	public String getUserFromHome(Principal principal){
		String redirect = String.format("redirect:/users/profile/%s", principal.getName());
		return redirect;
	}

	@GetMapping(value="/users/profile/{username}")
	public String showProfile(@PathVariable("username") String username, Principal principal, ModelMap model){
		
		User user = this.userService.findUser(username).get();
		Boolean isAdmin = userService.isAdmin(principal.getName());
		Player actualPlayer = this.playerService.findByUsername(principal.getName());

		List<FriendRequest> friendRequests = this.userService.getAllRequestsReceived(user);

		model.put("actual", actualPlayer);
		model.put("principalName", principal.getName());
		model.put("user", user);
		model.put("isAdmin", isAdmin);
		model.put("friendRequests", friendRequests);

		return VIEWS_USER_PROFILE;
	}

	@GetMapping(value="/users/edit/{username}")
	public String initUserEditForm(@PathVariable("username") String username, ModelMap model){
		User user = this.userService.findUser(username).get();
		model.put("user", user);
		return VIEWS_UPDATE_USER_PROFILE;
	}

	@PostMapping(value="/users/edit/{username}")
	public String processUserEditForm(@Valid User user, BindingResult result, @PathVariable("username") String username, ModelMap model){
		if(result.hasErrors()){
			model.put("user", user);
			model.put("errors", result.getAllErrors());
			return VIEWS_UPDATE_USER_PROFILE;
		} else{
			User userToUpdate = this.userService.findUser(username).get();
			BeanUtils.copyProperties(user, userToUpdate, "username","player","password","enabled","authorities", "friends"); 
			this.userService.saveUser(userToUpdate);
			
			return String.format("redirect:/users/profile/%s", username);                                                                        
		}
	}
	
	@GetMapping(value="/users/delete/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		userService.deleteByUsername(username);
		return "redirect:/";
	}

	@GetMapping(value = "/users/friends/sendRequest/{username}")
	public String sendFriendRequest(@PathVariable("username") String username, Principal principal){
		User recipient = this.userService.findUser(username).get();
		User sender = this.userService.findUser(principal.getName()).get();
		this.userService.sendFriendRequest(sender, recipient);
		return "redirect:/players/0";
	}

	@GetMapping(value = "/users/friends/denyRequest/{requestId}")
	public String denyFriendRequest(@PathVariable("requestId") String requestId, Principal principal){
		this.userService.deleteRequest(requestId);
		String redirect = String.format("redirect:/users/profile/%s", principal.getName());
		return redirect;
	}

	@GetMapping(value = "/users/friends/acceptRequest/{requestId}")
	public String acceptFriendRequest(@PathVariable("requestId") String requestId, Principal principal){
		this.userService.acceptRequest(requestId);
		String redirect = String.format("redirect:/users/profile/%s", principal.getName());
		return redirect;
	}

	@GetMapping(value="/users/friends/remove/{friendUsername}")
	public String removeFriend(@PathVariable("friendUsername") String friendUsername, Principal principal){
		User friend = this.userService.findUser(friendUsername).get();
		User user = this.userService.findUser(principal.getName()).get();
		this.userService.removeFriend(user, friend);

		String redirect = String.format("redirect:/users/profile/%s", principal.getName());
		return redirect;
	}

}
