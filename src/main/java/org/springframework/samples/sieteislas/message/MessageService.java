package org.springframework.samples.sieteislas.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    
    public void save(Message message) {
        this.messageRepository.save(message);
    }
    
    public Message getMessageById(Integer id) {
        return messageRepository.findById(id).get();
    }

    public void deleteMessageById(Integer id){
        messageRepository.deleteById(id);
    }
}
