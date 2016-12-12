package stevebullimore.tt.repo.cassandra;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import stevebullimore.tt.domain.Deck;
import stevebullimore.tt.domain.Theme;
import stevebullimore.tt.repo.TopTrumpsRepo;

@Repository
public class TopTrumpsCassandraRepo implements TopTrumpsRepo {
	static String[] CONTACT_POINTS = { "127.0.0.1" };
	static int PORT = 9042;

	private Cluster cluster;
	private Session session;
	private MappingManager manager;
	private Mapper<Deck> deckMapper;
	private Mapper<Theme> themeMapper;
	private DeckAccessor deckAccessor;

	@PostConstruct
	private void init() {
		cluster = Cluster.builder().addContactPoints(CONTACT_POINTS).withPort(PORT).build();
		session = cluster.connect();
		manager = new MappingManager(session);

		createKeySpace();

		deckMapper = manager.mapper(Deck.class);
		themeMapper = manager.mapper(Theme.class);
		deckAccessor = manager.createAccessor(DeckAccessor.class);
	}

	private void createKeySpace() {
		session.execute("CREATE KEYSPACE IF NOT EXISTS toptrumps WITH replication "
				+ "= {'class':'SimpleStrategy', 'replication_factor':1};");
		session.execute("CREATE TABLE IF NOT EXISTS toptrumps.themes (id uuid PRIMARY KEY, title text);");
		session.execute("CREATE TABLE IF NOT EXISTS toptrumps.decks (id uuid, theme_id uuid, title text, PRIMARY KEY (theme_id, title));");
	}

	@Override
	public List<Deck> getDecks(UUID themeId) {
		Result<Deck> r = deckAccessor.getAll(themeId);
		return r.all();
	}

	@Override
	public void createDeck(Deck deck) {
		deckMapper.save(deck);
	}

	@Override
	public void createTheme(Theme theme) {
		themeMapper.save(theme);
	}
}
