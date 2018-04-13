package net.johanneslink.scoreboard.console;

import net.johanneslink.scoreboard.core.*;

class Command {

	private Action action;
	private String[] strings;
	private String helpText;

	Command(Action action, String helpText, String... strings) {
		this.action = action;
		this.strings = strings;
		this.helpText = helpText;
	}

	// TODO: Not used
	Command(Action action) {
		this(action, "", "");
	}

	Action getAction() {
		return action;
	}

	String[] getStrings() {
		return this.strings;
	}

	String getHelpText() {
		return helpText;
	}

	// TODO: Do we need that? Could it be abstract?
	void execute(ScoreboardPresenter presenter, Console console){	}

	// TODO: Commented-out code, what's that?
//	void execute(ScoreboardPresenter presenter){}
//	void execute(Console console){}
}
