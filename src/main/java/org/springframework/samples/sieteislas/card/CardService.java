package org.springframework.samples.sieteislas.card;

import java.util.Collection;

import javax.transaction.Transactional;

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

    public void save(Card card) {
        this.cardRepository.save(card);
    }

    @Transactional
    public void delete(Card card) {
        card.setPlayer(null);
        this.cardRepository.delete(card);
    }

}
