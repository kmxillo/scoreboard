package net.johanneslink.scoreboard.gui;

import net.johanneslink.scoreboard.core.*;

import javax.swing.*;
import java.awt.*;

public class ScoreboardSwingApp extends JFrame implements ScoreboardView {

    private static final long serialVersionUID = 1L;

    private static Font SCORE_FONT = new Font("Courier New", Font.BOLD, 48);

    private JLabel scoreALabel;
    private JLabel scoreBLabel;
    private JButton teamAButton;
    private JButton teamBButton;
    private JButton score1Button;
    private JButton score2Button;
    private JButton score3Button;

    private final ScoreboardPresenter scoreboard;


    public ScoreboardSwingApp(ScoreboardPresenter scoreboard) {
        super("Basketball Console");
        this.scoreboard = scoreboard;
        initComponents();
        scoreboard.register(this);
    }

    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
        add(createTimeScorePanel(), BorderLayout.PAGE_START);
        add(createTeamButtonsPanel(), BorderLayout.CENTER);
        add(createScoringButtonsPanel(), BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 220);
    }

    private Component createScoringButtonsPanel() {
        JPanel scoringButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        score1Button = new JButton("1");
        score1Button.addActionListener(e -> {
            scoreboard.score(Points.One);
        });
        score2Button = new JButton("2");
        score2Button.addActionListener(e -> {
            scoreboard.score(Points.Two);
        });
        score3Button = new JButton("3");
        score3Button.addActionListener(e -> {
            scoreboard.score(Points.Three);
        });
        scoringButtonsPanel.add(score1Button);
        scoringButtonsPanel.add(score2Button);
        scoringButtonsPanel.add(score3Button);
        return scoringButtonsPanel;
    }

    private Component createTeamButtonsPanel() {
        JPanel teamButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        teamAButton = new JButton("Team A");
        teamAButton.addActionListener(e -> {
            scoreboard.select(Team.A);
        });
        teamBButton = new JButton("Team B");
        teamBButton.addActionListener(e -> {
            scoreboard.select(Team.B);
        });
        teamButtonsPanel.add(teamAButton);
        teamButtonsPanel.add(teamBButton);
        return teamButtonsPanel;
    }

    private JPanel createTimeScorePanel() {
        JPanel timeScorePanel = new JPanel(new BorderLayout(0, 0));
        timeScorePanel.add(createScorePanel(), BorderLayout.CENTER);
        return timeScorePanel;
    }

    private JPanel createScorePanel() {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scoreALabel = new JLabel();
        scoreALabel.setFont(SCORE_FONT);
        scoreBLabel = new JLabel();
        scoreBLabel.setFont(SCORE_FONT);
        scorePanel.add(scoreALabel);
        JLabel colon = new JLabel(":");
        colon.setFont(SCORE_FONT);
        scorePanel.add(colon);
        scorePanel.add(scoreBLabel);
        return scorePanel;
    }

    @Override
    public void displayScore(Score score) {
        scoreALabel.setText(score.a());
        scoreBLabel.setText(score.b());
    }

    @Override
    public void displaySelectedTeam(Team team) {
        teamAButton.setEnabled(team != Team.A);
        teamBButton.setEnabled(team != Team.B);
    }
}