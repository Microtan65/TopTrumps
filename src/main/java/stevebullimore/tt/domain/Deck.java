package stevebullimore.tt.domain;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "toptrumps", name = "decks",
readConsistency = "QUORUM",
writeConsistency = "QUORUM",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)
public final class Deck {
	private UUID id;
	
	@PartitionKey
	@Column(name = "theme_id")
	private UUID themeId;
	
	@ClusteringColumn
	private String title;
	
	public Deck() {
	}
	
	public Deck(UUID id, UUID theme_id, String name) {
		this.id = id;
		this.themeId = theme_id;
		this.title = name;
	}
	
	public UUID getThemeId() {
		return themeId;
	}
	public void setThemeId(UUID themeId) {
		this.themeId = themeId;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
	}


}
