package org.springframework.samples.sieteislas.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.samples.sieteislas.player.Player;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Audited
@Entity
@Table(name = "users")
public class User{
	
	@Id
	String username;
	
	@NotBlank
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "profile_image")
	private String profileImage;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	@NotAudited
	private Player player;

	@NotBlank
	@Column(name = "password")
	@NotAudited
	String password;
	
	@NotAudited
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@NotAudited
	private Set<Authorities> authorities;

  
	@ManyToMany
	@JoinTable(name="friends", 
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="friend_id"))
	private List<User> friends;
	
	@ManyToMany
	@JoinTable(name="friends", 
				joinColumns = @JoinColumn(name="friend_id"),
				inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> friendOf;

	
	public static User createDefault() {
		
		User user = new User();
		
		user.setAuthorities(new HashSet<>());
		user.setEnabled(true);
		user.setFirstName("Default");
		user.setLastName("Default");
		user.setFriendOf(new ArrayList<>());
		user.setFriends(new ArrayList<>());
		user.setPassword("default");
		user.setPlayer(null);
		user.setProfileImage("");
		user.setUsername("defaultUsername");
		
		return user;
	}

}
