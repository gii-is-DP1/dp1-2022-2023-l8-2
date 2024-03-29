package org.springframework.samples.sieteislas.player;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.sieteislas.user.User;
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
    
    public Player findByUsername(String username) {
		return this.playerRepository.findPlayerByUsername(username);
	}

    public void save(@Valid Player player) {
		this.playerRepository.save(player);
	}
    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.findAllPlayers(pageable);
    }

    public Player findById(Integer playerId) {
		return this.playerRepository.findById(playerId).get();
	}
 

}
