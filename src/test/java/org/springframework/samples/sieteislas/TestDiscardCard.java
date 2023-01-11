package org.springframework.samples.sieteislas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.card.CardRepository;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameController;
import org.springframework.samples.sieteislas.game.GameRepository;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
@Sql(scripts = "/test.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestDiscardCard {
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private GameController gameController;
	
	@Autowired
	private ModelMap model;
	
	@Autowired
	private Principal principal;
	
	@Test
	public void testDiscardCard() {
		getPossibleChoices();
	}
	
	@Transactional
	private void getPossibleChoices() {
		
		Game game = gameRepo.findById(1).get();
		
		Player player1 = playerRepo.findById(1).get();
		Player player2 = playerRepo.findById(2).get();
		
		List<Player> players = new ArrayList<>();
		players.add(player1); players.add(player2);
		
		game.setCreatorUsername(player1.getUser().getUsername());
		game.setPlayers(players);
		
		gameService.createDeck(game);
		
		game.setPlayerTurn(0);
		game.setDiceRoll(3);
		
		Boolean successfullyDiscarded = setPlayerCards(game, player2);
		
		assertTrue(successfullyDiscarded);
	}
	
	private Boolean setPlayerCards(Game game, Player p) {
		
		List<Card> cards = new ArrayList<>();
		cardRepo.findAll().iterator().forEachRemaining(c->cards.add(c));
		
		List<Card> playerCards = cards.subList(0, 3);
		
		p.setCards(playerCards);
		Integer idCardToDiscard = p.getCards().get(0).getId();
		
		gameController.discardCard(game.getId().toString(),
				idCardToDiscard.toString(), model, principal);
		
		return successfullyDiscarded(playerCards, p.getCards(), idCardToDiscard);
	}
	
	private Boolean successfullyDiscarded(List<Card> cardsBefore,
			List<Card> cardsAfter, Integer cardToDiscardId) {
		
		Map<Integer, Long> countBefore = cardsBefore.stream()
				.map(Card::getId)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()));
		
		Map<Integer, Long> countAfter = cardsAfter.stream()
				.map(Card::getId)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()));
		
		return countBefore.get(cardToDiscardId) - 1
				== countAfter.get(cardToDiscardId);
	}
	
}
