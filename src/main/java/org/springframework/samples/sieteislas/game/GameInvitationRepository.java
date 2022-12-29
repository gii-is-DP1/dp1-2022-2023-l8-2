package org.springframework.samples.sieteislas.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameInvitationRepository extends CrudRepository<GameInvitation, Integer>{

    @Query("SELECT i FROM GameInvitation i WHERE i.guest.username LIKE :name")
    List<GameInvitation> findAllByUser(String name);
    
}
