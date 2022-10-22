package org.springframework.samples.petclinic.message;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="messages")
public class Message extends BaseEntity{
    
    private String body;
    private LocalDateTime date;

}
