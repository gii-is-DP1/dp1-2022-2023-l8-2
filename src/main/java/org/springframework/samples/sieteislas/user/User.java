package org.springframework.samples.sieteislas.user;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.samples.sieteislas.player.Player;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy = "uuid2")
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
}
