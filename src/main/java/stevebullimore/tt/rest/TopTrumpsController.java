package stevebullimore.tt.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import stevebullimore.tt.domain.Deck;
import stevebullimore.tt.domain.Theme;
import stevebullimore.tt.repo.TopTrumpsRepo;

@RestController
public class TopTrumpsController {
	@Autowired
	TopTrumpsRepo repo;
	
	@RequestMapping(value = "/theme", method = RequestMethod.POST)
	public Theme createTheme(@RequestBody Theme theme) {
		Theme newTheme = new Theme(UUID.randomUUID(), theme.getTitle());
		repo.createTheme(newTheme);
		return newTheme;
	}
	
	@RequestMapping(value = "/deck", method = RequestMethod.POST)
	public Deck createDeck(@RequestBody Deck deck) {
		Deck newDeck = new Deck(UUID.randomUUID(), deck.getThemeId(), deck.getTitle());
		repo.createDeck(newDeck);
		return newDeck;
	}

	@RequestMapping(value = "/deck/{themeId}", method = RequestMethod.GET)
	public List<Deck> getAllDecks(@PathVariable String themeId) {
		return repo.getDecks(UUID.fromString(themeId));
	}
}
