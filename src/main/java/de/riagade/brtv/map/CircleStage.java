package de.riagade.brtv.map;

import java.util.Optional;

public enum CircleStage {
	CIRCLE_1( 2/10f),
	CIRCLE_2( 4/10f),
	CIRCLE_3(6/10f),
	CIRCLE_4(8/10f),
	CIRCLE_5(10/10f),
	CIRCLE_6(10/10f);

	private static final float PORTION_SIZE = 1/3f;

	private final double deathPercentage;

	CircleStage(float deathPercentage) {
		this.deathPercentage = deathPercentage;
	}

	public Optional<CircleStage> next() {
		var nextOrdinal = ordinal() + 1;
		return nextOrdinal < values().length ? Optional.of(values()[nextOrdinal]) : Optional.empty();
	}

	public Optional<CircleStage> previous() {
		return ordinal() == 0 ? Optional.empty() : Optional.of(values()[ordinal() - 1]);
	}

	public static double getDiameterPercentage(CircleStage current) {
		var factor = current.ordinal() + 1;
		return factor == values().length ? 0 : Math.pow(PORTION_SIZE, factor);
	}

	public double getDeathPercentage() {
		return deathPercentage;
	}
}
