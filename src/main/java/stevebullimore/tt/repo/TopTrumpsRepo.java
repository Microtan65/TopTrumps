package stevebullimore.tt.repo;

import java.util.List;
import java.util.UUID;

import stevebullimore.tt.domain.Deck;
import stevebullimore.tt.domain.Theme;

public interface TopTrumpsRepo {
	void createTheme(Theme theme);
	void createDeck(Deck deck);
	List<Deck> getDecks(UUID themeId);
}
