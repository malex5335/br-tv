package de.riagade.brtv.map;

import de.riagade.brtv.player.Position;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {

	GameMap gameMap;

	@BeforeEach
	void setUp() {
		gameMap = new GameMap(1_000, 0);
	}

	@Test
	void viewLoot() {
		var loot = gameMap.getLootMap();
		var commonLoot = loot.values().stream()
				.filter(LootRarity.COMMON::equals)
				.count();
		var rareLoot = loot.values().stream()
				.filter(LootRarity.RARE::equals)
				.count();
		var epicLoot = loot.values().stream()
				.filter(LootRarity.EPIC::equals)
				.count();
		var legendaryLoot = loot.values().stream()
				.filter(LootRarity.LEGENDARY::equals)
				.count();
		var totalLoot = commonLoot + rareLoot + epicLoot + legendaryLoot;

		System.out.println("Common loot: " + commonLoot);
		System.out.println("Rare loot: " + rareLoot);
		System.out.println("Epic loot: " + epicLoot);
		System.out.println("Legendary loot: " + legendaryLoot);
		System.out.println("Total loot: " + totalLoot);

		for(var x = 0; x < gameMap.getSize(); x++) {
			for(var y = 0; y < gameMap.getSize(); y++) {
				var pos = new Position(x, y);
				if(loot.containsKey(pos))
					System.out.print(getIndicator(loot.get(pos)));
				else
					System.out.print(".");
			}
			System.out.println();
		}
	}

	private String getIndicator(LootRarity lootRarity) {
		return switch (lootRarity){
			case COMMON -> "C";
			case RARE -> "R";
			case EPIC -> "E";
			case LEGENDARY -> "L";
		};
	}
}