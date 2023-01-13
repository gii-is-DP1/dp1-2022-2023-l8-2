package org.springframework.samples.sieteislas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.configuration.SecurityConfiguration;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerService;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.samples.sieteislas.user.UserController;
import org.springframework.samples.sieteislas.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.FilterType;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration= SecurityConfiguration.class)
@WithMockUser(roles="ADMIN")
public class TestUserController {
    @MockBean
    UserService userService;
    @MockBean
    PlayerService playerService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws Exception{
		User u1 = new User();
		u1.setUsername("pabberima");
		u1.setFirstName("Pablo");		
		u1.setLastName("Bermudez");
		u1.setPassword("test1");
		u1.setEnabled(true);
		u1.setPlayer(new Player());
		
		User u2 = new User();
		u2.setUsername("juanlo");
		u2.setFirstName("Juan");		
		u2.setLastName("Lo");
		u2.setPassword("test2");
		u2.setEnabled(true);
		given(this.userService.findUser("pabberima").get()).willReturn(u1);
		given(this.userService.findUser("juanlo").get()).willReturn(u2);
		//given(this.userService.sendFriendRequest(u1, u2)).willReturn(void);

	}
    
    @Test
    public void testSendFriendRequest() throws Exception {
        mockMvc.perform(get("/users/friends/sendRequest/{username}", "pabberima"))
            //.andExpect(model().attributeExists("gameStatistics"));
            //.andExpect(model().attributeExists("playerPointsMaps"))
            .andExpect(status().isOk())
            .andExpect(redirectedUrl("/players/"));
    }

}
