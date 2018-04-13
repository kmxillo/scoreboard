package net.johanneslink.scoreboard.gui;

import net.johanneslink.scoreboard.core.Score;
import net.johanneslink.scoreboard.core.ScoreboardPresenter;
import org.junit.jupiter.api.*;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuiTest {

    private JFrameOperator mainFrame;

    @BeforeEach
    void init() {
        ScoreboardPresenter scp = new ScoreboardPresenter();
        ScoreboardSwingApp app = new ScoreboardSwingApp(scp);
        app.setVisible(true);
        mainFrame = new JFrameOperator("Basketball Console");
    }

    @AfterEach
    void release() {
        mainFrame.close();
    }

    @Test
    void appStart() {
        Assertions.assertEquals("Basketball Console", mainFrame.getTitle());
    }

    @Test
    void teamAScores1() {
        click(getTeamAButton());
        click(getScore1Button());
        assertScore(1, 0);
    }

    @Test
    void teamBScores3() {
        click(getTeamBButton());
        click(getScore3Button());
        assertScore(0, 3);
    }

    @Test
    void bothTeamsScoreTwice() {
        click(getTeamBButton());
        click(getScore3Button());
        click(getTeamAButton());
        click(getScore2Button());
        click(getTeamAButton());
        click(getScore1Button());
        click(getTeamBButton());
        click(getScore3Button());
        assertScore(3, 6);
    }

    @Test
    void pressingScoreButtonsWithoutTeamButtonsDoesNotChangeScore() {
        click(getScore1Button());
        click(getScore1Button());
        click(getScore2Button());
        click(getScore3Button());
        assertScore(0, 0);
    }

    @Test
    void lastPressedTeamButtonCounts() {
        click(getTeamAButton());
        click(getTeamBButton());
        click(getScore1Button());
        assertScore(0, 1);
    }

    private void assertScore(int scoreA, int scoreB) {
        Assertions.assertAll(
            () -> assertEquals(scoreA, Integer.parseInt(getTeamAScore()), "score team A"),
            () -> assertEquals(scoreB, Integer.parseInt(getTeamBScore()), "score team B")
        );
    }

    private void click(JButtonOperator button) {
        button.clickMouse();
    }

    private String getTeamAScore() {
        return new JLabelOperator(mainFrame, 0).getText();
    }

    private String getTeamBScore() {
        return new JLabelOperator(mainFrame, 2).getText();
    }

    private JButtonOperator getScore1Button() {
        return new JButtonOperator(mainFrame, "1");
    }

    private JButtonOperator getScore2Button() {
        return new JButtonOperator(mainFrame, "2");
    }

    private JButtonOperator getScore3Button() {
        return new JButtonOperator(mainFrame, "3");
    }

    private JButtonOperator getTeamAButton() {
        return new JButtonOperator(mainFrame, "Team A");
    }

    private JButtonOperator getTeamBButton() {
        return new JButtonOperator(mainFrame, "Team B");
    }
}
