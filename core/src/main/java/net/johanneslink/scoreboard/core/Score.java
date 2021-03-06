package net.johanneslink.scoreboard.core;

public class Score {

    public static Score ab(int teamA, int teamB) {
        if (teamA < 0 || teamB < 0) throw new IllegalArgumentException("Scores cannot be negative");
        return new Score(teamA, teamB);
    }

    private int teamA;
    private int teamB;

    private Score(int teamA, int teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    @Override
    public int hashCode() {
        return teamA + teamB >> 8;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Score other = (Score) obj;
        return teamA == other.teamA && teamB == other.teamB;
    }

    @Override
    public String toString() {
        return a() + ":" + b();
    }

    public String a() {
        return formatScore(teamA);
    }

    public String b() {
        return formatScore(teamB);
    }

    private String formatScore(int score) {
        return String.format("%03d", score);
    }

    Score changeTeamBy(Team team, Points points) {
        if (team == Team.A) {
            return ab(points.useToChange(teamA), teamB);
        } else if (team == Team.B) {
            return ab(teamA, points.useToChange(teamB));
        }
        return new Score(teamA, teamB);
    }
}
