package org.springframework.samples.sieteislas.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FriendRequestRepositoy extends CrudRepository<FriendRequest, Integer>{
    
    @Query("SELECT fr FROM FriendRequest fr WHERE fr.sender LIKE :user")
    List<FriendRequest> getAllFriendRequestsSentByUser(@Param("user") User user);

    @Query("SELECT fr FROM FriendRequest fr WHERE fr.recipient LIKE :user")
    List<FriendRequest> getAllFriendRequestsReceivedByUser(@Param("user") User user);
}
