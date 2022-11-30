package org.springframework.samples.sieteislas.card;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    
    public Collection<Card> getAllCards() {
	    return (Collection<Card>) cardRepository.findAll();
	}
    
    public Card findById(Integer cardId) {
		return this.cardRepository.findById(cardId).get();
	}

}
