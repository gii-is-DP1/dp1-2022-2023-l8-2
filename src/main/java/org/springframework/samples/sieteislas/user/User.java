package org.springframework.samples.sieteislas.user;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.springframework.samples.sieteislas.player.Player;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	
	@Id
	String username;
	
	@NotBlank
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "profile_image")
	private String profileImage;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Player player;

	@NotBlank
	@Column(name = "password")
	String password;
	
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
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
}
