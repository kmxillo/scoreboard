package net.johanneslink.scoreboard.console;

class Command {

	private Action action;
	private String[] strings;
	private String helpText;

	Command(Action action, String helpText, String... strings) {
		this.action = action;
		this.strings = strings;
		this.helpText = helpText;
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
}
