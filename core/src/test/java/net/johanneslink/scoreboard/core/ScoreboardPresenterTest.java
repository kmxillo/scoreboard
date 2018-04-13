package net.johanneslink.scoreboard.core;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardPresenterTest implements ScoreboardView {

	private ScoreboardPresenter presenter;
	private Team lastSelectedTeam;
	private Score lastDisplayedScore;

	@BeforeEach
	void initialize() {
		presenter = new ScoreboardPresenter();
		lastSelectedTeam = null;
		lastDisplayedScore = null;
		presenter.register(this);
	}

	@Test
	void initialScoreIs0to0() {
		assertEquals(lastDisplayedScore, Score.ab(0, 0));
	}

	@Test
	void initiallyNoTeamIsSelected() {
		assertEquals(lastSelectedTeam, Team.NONE);
	}

	@Test
	void selectingTeamADisplaysSelection() {
		presenter.select(Team.A);
		assertEquals(lastSelectedTeam, Team.A);
	}

	@Test
	void selectingTeamBDisplaysSelection() {
		presenter.select(Team.B);
		assertEquals(lastSelectedTeam, Team.B);
	}

	@Test
	void scoreInitialPointForTeamA() {
		presenter.select(Team.A);
		presenter.score(Points.One);
		assertEquals(lastDisplayedScore, Score.ab(1, 0));
	}

	@Test
	void plusForTeamA() {
		presenter.select(Team.A);
		presenter.plus();
		assertEquals(lastDisplayedScore, Score.ab(1, 0));
	}

	@Test
	void afterScoringSameTeamStaysSelected() {
		presenter.select(Team.A);
		presenter.score(Points.One);
		assertEquals(Team.A, lastSelectedTeam);

		presenter.select(Team.B);
		presenter.score(Points.Two);
		assertEquals(Team.B, lastSelectedTeam);
	}

	@Test
	void score2PointsForTeamA() {
		presenter.select(Team.A);
		presenter.score(Points.One);
		presenter.select(Team.A);
		presenter.score(Points.Two);
		assertEquals(lastDisplayedScore, Score.ab(3, 0));
	}

	@Test
	void score3PointsForTeamA() {
		presenter.select(Team.A);
		presenter.score(Points.Two);
		presenter.select(Team.A);
		presenter.score(Points.Three);
		assertEquals(lastDisplayedScore, Score.ab(5, 0));
	}

	@Test
	void scoreInitialPointForTeamB() {
		presenter.select(Team.B);
		presenter.score(Points.Three);
		assertEquals(lastDisplayedScore, Score.ab(0, 3));
	}

	@Test
	void scorePointsForTeamBLater() {
		presenter.setScore(Score.ab(22, 33));
		presenter.select(Team.B);
		presenter.score(Points.One);
		assertEquals(lastDisplayedScore, Score.ab(22, 34));
		presenter.select(Team.B);
		presenter.score(Points.Two);
		assertEquals(lastDisplayedScore, Score.ab(22, 36));
	}

	@Test
	void settingScoreWillTriggerDisplayScore() {
		presenter.setScore(Score.ab(22, 33));
		assertEquals(lastDisplayedScore, Score.ab(22, 33));
	}

	@Test
	void ignoreScoreChangeAttemptWhenNoTeamIsSelected() {
		presenter.setScore(Score.ab(22, 33));
		presenter.score(Points.One);
		presenter.score(Points.Two);
		presenter.score(Points.Three);
		presenter.plus();
		assertEquals(lastDisplayedScore, Score.ab(22, 33));

		// Check that presenter's internal score has not been changed
		presenter.select(Team.B);
		presenter.score(Points.One);
		assertEquals(lastDisplayedScore, Score.ab(22, 34));
	}

	@Override
	public void displaySelectedTeam(Team team) {
		lastSelectedTeam = team;
	}

	@Override
	public void displayScore(Score score) {
		lastDisplayedScore = score;
	}
}
