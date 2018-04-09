package net.johanneslink.scoreboard.core;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

	@Test
	void negativeScoreForTeamAIsNotAllowed() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Score.ab(-1, 1);
		});
	}

	@Test
	void negativeScoreForTeamBIsNotAllowed() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Score.ab(1, -1);
		});
	}

	@Test
	void scoreIsFormattedWith3Digits() {
		assertEquals(Score.ab(1, 9).toString(), "001:009");
		assertEquals(Score.ab(11, 99).toString(), "011:099");
		assertEquals(Score.ab(111, 999).toString(), "111:999");
	}

	@Test
	void incrementScoreOfTeamA() {
		Score score = Score.ab(12, 33);
		assertEquals(score.incTeamABy(Points.One), Score.ab(13, 33));
		assertEquals(score.incTeamABy(Points.Two), Score.ab(14, 33));
		assertEquals(score.incTeamABy(Points.Three), Score.ab(15, 33));
	}

	@Test
	void incrementScoreOfTeamB() {
		Score score = Score.ab(44, 99);
		assertEquals(score.incTeamBBy(Points.One), Score.ab(44, 100));
		assertEquals(score.incTeamBBy(Points.Two), Score.ab(44, 101));
		assertEquals(score.incTeamBBy(Points.Three), Score.ab(44, 102));
	}
}
