package org.springframework.samples.sieteislas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.user.FriendRequest;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.samples.sieteislas.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
@Sql(scripts = "/test.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestUserService {
    @Autowired
    private UserService userService;
		

    @Test
    @Transactional
	void testSendFriendRequest() {
    	User recipient = this.userService.findUser("testplayer1").get();
		User sender = this.userService.findUser("testplayer2").get();
		this.userService.sendFriendRequest(sender, recipient);
		assertEquals(this.userService.getAllRequestsReceived(recipient).size(), 1);
    }
	
    
    @Test
    void testAllRequestSent() {
    	User sender = this.userService.findUser("testplayer1").get();
    	User sent2 = this.userService.findUser("testplayer2").get();
    	User sent3 = this.userService.findUser("testplayer3").get();
    	this.userService.sendFriendRequest(sender, sent2);
    	this.userService.sendFriendRequest(sender, sent3);
    	
    	List<FriendRequest> sent= this.userService.getAllRequestsSent(sender);
		assertThat(sent.get(0).getRecipient().getUsername()).isEqualTo("testplayer2");
		assertThat(sent.get(1).getRecipient().getUsername()).isEqualTo("testplayer3");
	}
    
    @Test
    void testAllRequestReceived() {
    	User sent = this.userService.findUser("testplayer1").get();
    	User sender2 = this.userService.findUser("testplayer2").get();
    	User sender3 = this.userService.findUser("testplayer3").get();
    	this.userService.sendFriendRequest(sender2, sent);
    	this.userService.sendFriendRequest(sender3, sent);
    	
    	List<FriendRequest> received= this.userService.getAllRequestsReceived(sent);
		assertThat(received.get(0).getSender().getUsername()).isEqualTo("testplayer2");
		assertThat(received.get(1).getSender().getUsername()).isEqualTo("testplayer3");
	}
    
    @Test
    @Transactional
    void testAcceptRequest() {
    	User sent = this.userService.findUser("testplayer1").get();
    	User sender2 = this.userService.findUser("testplayer2").get();
    	this.userService.sendFriendRequest(sender2, sent);
    	List<FriendRequest> received= this.userService.getAllRequestsReceived(sent);
        
    	this.userService.acceptRequest(received.get(0).getId().toString());
		assertThat(sent.getFriendOf().contains(sender2));
		assertThat(sent.getFriends().contains(sender2));
		assertThat(this.userService.getAllRequestsReceived(sent)).isEmpty();
	}
    
    @Test
    @Transactional
    void testDeclineRequest() {
    	User sent = this.userService.findUser("testplayer1").get();
    	User sender2 = this.userService.findUser("testplayer2").get();
    	this.userService.sendFriendRequest(sender2, sent);
    	List<FriendRequest> received= this.userService.getAllRequestsReceived(sent);
        
    	this.userService.deleteRequest(received.get(0).getId().toString());
		assertThat(sent.getFriendOf()).isEmpty();
		assertThat(sent.getFriends()).isEmpty();
	}
    
    @Test
    @Transactional
    void testAddFriend() {
    	User user1 = this.userService.findUser("testplayer1").get();
    	User user2 = this.userService.findUser("testplayer2").get();
    	this.userService.addFriend(user1, user2);
    	
    	assertEquals(user1.getFriends().size(), 1);
    	assertEquals(user1.getFriendOf().size(), 0);
		assertEquals(user1.getFriends().get(0).getUsername(), "testplayer2");
	}
    
    @Test
    @Transactional
    void testRemoveFriend() {
    	User user1 = this.userService.findUser("testplayer1").get();
    	User user2 = this.userService.findUser("testplayer2").get();
    	this.userService.addFriend(user1, user2);
    	this.userService.removeFriend(user1, user2);
    	
    	assertEquals(user1.getFriends().size(), 0);
    	assertEquals(user1.getFriendOf().size(), 0);
	}
    

}
