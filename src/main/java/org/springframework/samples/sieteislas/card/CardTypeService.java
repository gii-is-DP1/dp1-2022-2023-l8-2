package org.springframework.samples.sieteislas.card;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTypeService {

    private final CardTypeRepository cardTypeRepository;

    @Autowired
    public CardTypeService(CardTypeRepository cardTypeRepository) {
        this.cardTypeRepository = cardTypeRepository;
    }
    
    public Collection<CardType> getAllCardTypes() {
	    return (Collection<CardType>) cardTypeRepository.findAll();
	}
    
    public void findById(Integer cardTypeId) {
		this.cardTypeRepository.findById(cardTypeId).get();
	}

}
