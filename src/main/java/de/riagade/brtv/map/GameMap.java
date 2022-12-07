package de.riagade.brtv.map;

import de.riagade.brtv.player.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class GameMap {
	private Map<Position, LootRarity> lootMap;
	private List<Player> players;
	private Position circlePosition;
	private CircleStage circleStage;
	private int size;
	private Random random;

	public GameMap(int size, int playerCount) {
		setRandom(new Random(System.currentTimeMillis()));
		setSize(size);
		setLootMap(spawnLoot());
		setCircleStage(CircleStage.CIRCLE_1);
		setRandomCirclePosition(getCircleStage());
		setPlayers(dropPlayers(playerCount));
	}

	private Map<Position, LootRarity> spawnLoot() {
		var lootMap = new HashMap<Position, LootRarity>();
		var lootCount = (int) (getSize() * getSize() * 0.15);
		for (int i = 0; i < lootCount; i++) {
			var position = getRandomPosition(getSize(), 15);
			lootMap.put(position, LootRarity.getRandomRarity(getRandom()));
		}
		return lootMap;
	}

	private void setRandomCirclePosition(CircleStage circleStage) {
		if(getCirclePosition() != null) {
			getRandomPosition(getCircleDiameter(circleStage), getCircleRadius(circleStage));
		}
		setCirclePosition(getRandomPosition(getSize(), getCircleRadius(circleStage)));
	}

	private int getCircleRadius(CircleStage circleStage) {
		return getCircleDiameter(circleStage) / 2;
	}

	private int getCircleDiameter(CircleStage circleStage) {
		return (int) (getSize() * CircleStage.getDiameterPercentage(circleStage));
	}

	private List<Player> dropPlayers(int playerCount) {
		return new ArrayList<>();
	}



	private Position getRandomPosition(int size, int border) {
		var x = getRandom().nextInt(size - border * 2) + border;
		var y = getRandom().nextInt(size - border * 2) + border;
		return new Position(x, y);
	}
}
