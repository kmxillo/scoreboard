package net.johanneslink.scoreboard.gui;

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

    @Disabled
    @Test
    void teamAScores1() {
        click(getTeamAButton());
        click(getScore1Button());
        assertScore(1, 0);
    }

    @Disabled
    @Test
    void teamBScores3() {
        click(getTeamBButton());
        click(getScore3Button());
        assertScore(0, 3);
    }

    @Disabled
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

    @Disabled
    @Test
    void pressingScoreButtonsWithoutTeamButtonsDoesNotChangeScore() {
        click(getTeamAButton());
        click(getScore1Button());
        click(getScore1Button());
        click(getScore2Button());
        click(getScore3Button());
        assertScore(1, 0);
    }

    @Disabled
    @Test
    void lastPressedTeamButtonCounts() {
        click(getTeamAButton());
        click(getTeamBButton());
        click(getScore1Button());
        assertScore(0, 1);
    }

    private void assertScore(int scoreA, int scoreB) {
        assertEquals(getTeamAScore(), Integer.toString(scoreA), "score team A");
        assertEquals(getTeamBScore(), Integer.toString(scoreB), "score team B");
    }

    private void click(JButtonOperator button) {
        button.clickMouse();
    }

    private String getTeamAScore() {
        return new JLabelOperator(mainFrame, 1).getText();
    }

    private String getTeamBScore() {
        return new JLabelOperator(mainFrame, 3).getText();
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
