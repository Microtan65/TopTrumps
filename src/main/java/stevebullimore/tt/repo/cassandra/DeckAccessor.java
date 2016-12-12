package stevebullimore.tt.repo.cassandra;

import java.util.UUID;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

import stevebullimore.tt.domain.Deck;

@Accessor
public interface DeckAccessor {
	@Query("SELECT * FROM toptrumps.decks where theme_id = ?")
	Result<Deck> getAll(UUID themeId);
}
