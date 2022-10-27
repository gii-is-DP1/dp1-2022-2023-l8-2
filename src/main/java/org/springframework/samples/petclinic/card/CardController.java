package org.springframework.samples.petclinic.card;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cards")
public class CardController {
    
    private final CardService cardService;

    public CardController(CardService cardService){
        this.cardService = cardService;
    }

}