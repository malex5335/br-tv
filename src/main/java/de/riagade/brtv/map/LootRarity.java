package de.riagade.brtv.map;

import java.util.Random;

public enum LootRarity {
	COMMON(8_890/10_000f), RARE(1_000/10_000f), EPIC(100/10_000f), LEGENDARY(10/10_000f);

	private final float rarity;
	LootRarity(float rarity) {
		this.rarity = rarity;
	}

	public static LootRarity getRandomRarity(Random random) {
		var randomValue = random.nextFloat();
		var rarity = 0f;
		for (var lootRarity : values()) {
			rarity += lootRarity.rarity;
			if (randomValue < rarity) {
				return lootRarity;
			}
		}
		return COMMON;
	}

	public float getRarity() {
		return rarity;
	}
}
