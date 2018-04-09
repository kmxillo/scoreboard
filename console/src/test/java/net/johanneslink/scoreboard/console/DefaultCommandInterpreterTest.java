package net.johanneslink.scoreboard.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultCommandInterpreterTest {

	private final CommandInterpreter interpreter = new DefaultCommandInterpreter();

	@Test
	void interpretQuit() {
		assertEquals(interpreter.parse("quit"), Action.QUIT);
		assertEquals(interpreter.parse("QUIT"), Action.QUIT);
		assertEquals(interpreter.parse("q"), Action.QUIT);
		assertEquals(interpreter.parse("Q"), Action.QUIT);
	}

	@Test
	void interpretTeamASelection() {
		assertEquals(interpreter.parse("a"), Action.SELECT_A);
		assertEquals(interpreter.parse("select a"), Action.SELECT_A);
	}

	@Test
	void interpretTeamBSelection() {
		assertEquals(interpreter.parse("b"), Action.SELECT_B);
		assertEquals(interpreter.parse("select b"), Action.SELECT_B);
	}

	@Test
	void interpretScore1() {
		assertEquals(interpreter.parse("1"), Action.SCORE_1);
		assertEquals(interpreter.parse("score 1"), Action.SCORE_1);
	}

	@Test
	void interpretScore2() {
		assertEquals(interpreter.parse("2"), Action.SCORE_2);
		assertEquals(interpreter.parse("score 2"), Action.SCORE_2);
	}

	@Test
	void interpretHelp() {
		assertEquals(interpreter.parse("?"), Action.HELP);
		assertEquals(interpreter.parse("h"), Action.HELP);
		assertEquals(interpreter.parse("help"), Action.HELP);
	}

	@Test
	void interpretUnknownCommands() {
		assertEquals(interpreter.parse("c"), Action.UNKNOWN);
		assertEquals(interpreter.parse("xxx"), Action.UNKNOWN);
	}
}
