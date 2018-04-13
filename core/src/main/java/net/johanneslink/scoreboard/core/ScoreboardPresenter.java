package net.johanneslink.scoreboard.core;

public class ScoreboardPresenter {

	private ScoreboardView view;
	private Score currentScore = Score.ab(0, 0);
	private Team currentSelectedTeam = Team.NONE;

	public void register(ScoreboardView view) {
		this.view = view;
		displayCurrentScore();
		displaySelectedTeam();
	}

	public void select(Team team) {
		currentSelectedTeam = team;
		displaySelectedTeam();
	}

	public void score(Points points) {
		if (currentSelectedTeam == Team.A) {
			currentScore = currentScore.incTeamABy(points);
			displayCurrentScore();
		}
		if (currentSelectedTeam == Team.B) {
			currentScore = currentScore.incTeamBBy(points);
			displayCurrentScore();
		}
	}

	public void setScore(Score newScore) {
		currentScore = newScore;
		displayCurrentScore();
	}

	private void displayCurrentScore() {
		view.displayScore(currentScore);
	}

	private void displaySelectedTeam() {
		view.displaySelectedTeam(currentSelectedTeam);
	}

	public void plus() {
		score(Points.One);
	}
}
