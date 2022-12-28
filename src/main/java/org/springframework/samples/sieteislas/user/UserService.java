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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all SieteIsla controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {

	private UserRepository userRepository;
	private FriendRequestRepositoy friendRequestRepository;

	@Autowired
	public UserService(UserRepository userRepository, FriendRequestRepositoy friendRequestRepository) {
		this.userRepository = userRepository;
		this.friendRequestRepository = friendRequestRepository;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}

	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}
	
	public void deleteByUsername(String username) {
		User userToDelete = findUser(username).get();
		userRepository.delete(userToDelete);
	}
	
	public Boolean isAdmin(String username) {
		User userToCheck = findUser(username).get();
		return userToCheck.getAuthorities().stream()
				.map(Authorities::getAuthority)
				.anyMatch(a -> a.equals("admin"));
	}

    public List<User> notPlaying(List<User> friends) {
        return friends.stream()
						.filter(x-> x.getPlayer().getGame()==null)
						.collect(Collectors.toList());
    }


	public void sendFriendRequest(User sender, User recipient) {
		FriendRequest fr = new FriendRequest();
		fr.setSender(sender);
		fr.setRecipient(recipient);
		this.friendRequestRepository.save(fr);
    }

    public List<FriendRequest> getAllRequestsSent(User user) {
        return this.friendRequestRepository.getAllFriendRequestsSentByUser(user);
    }

    public List<FriendRequest> getAllRequestsReceived(User user) {
        return this.friendRequestRepository.getAllFriendRequestsReceivedByUser(user);
    }

	public List<String> friendOrHasBeenSentARequest(User user) {
		List<FriendRequest> requestsSent = getAllRequestsSent(user);
		List<User> sentTo = requestsSent.stream()
										.map(x->x.getRecipient())
										.collect(Collectors.toList());
		List<User> friends = user.getFriends();
		friends.addAll(sentTo);
		List<String> friendsOrHasBeenSentARequest = friends.stream()
															.map(u-> u.getUsername())
															.collect(Collectors.toList());
		return friendsOrHasBeenSentARequest;
    }
	
    public void deleteRequest(String requestId) {
        FriendRequest request = this.friendRequestRepository.findById(Integer.valueOf(requestId)).get();
		this.friendRequestRepository.delete(request);
    }

    public void acceptRequest(String requestId) {
        FriendRequest request = this.friendRequestRepository.findById(Integer.valueOf(requestId)).get();
        addFriend(request.getSender(), request.getRecipient());
        addFriend(request.getRecipient(), request.getSender());

        this.friendRequestRepository.delete(request);
    }

	public void addFriend(User sender, User recipient) {
		sender.getFriends().add(recipient);
		this.userRepository.save(sender);
    }

	public void removeFriend(User user, User friend) {
		user.getFriends().remove(friend);
		friend.getFriends().remove(user);
		this.userRepository.save(user);
		this.userRepository.save(friend);
    }

}
