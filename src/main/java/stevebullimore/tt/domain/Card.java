package stevebullimore.tt.domain;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "toptrumps", name = "cards",
readConsistency = "QUORUM",
writeConsistency = "QUORUM",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)
public final class Card {
	private UUID id;
	
	@PartitionKey
	@Column(name = "deck_id")
	private UUID deckId;
	
	@ClusteringColumn
	private String name;
	
	public Card() {
	}
	
	public Card(UUID id, UUID deck_id, String name) {
		this.id = id;
		this.deckId = deck_id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getDeckId() {
		return deckId;
	}

	public void setDeckId(UUID deckId) {
		this.deckId = deckId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
