package org.springframework.samples.petclinic.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
	private PlayerRepository playerRepository;

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

    public Player findByUser(User user) {
		return this.playerRepository.findPlayerByUser(user);
	}

}
