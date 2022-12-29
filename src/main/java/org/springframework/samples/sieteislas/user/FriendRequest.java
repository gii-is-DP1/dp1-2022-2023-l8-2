package org.springframework.samples.sieteislas.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.samples.sieteislas.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class FriendRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="friend_request_sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name="friend_request_recipient")
    private User recipient;
    
}
