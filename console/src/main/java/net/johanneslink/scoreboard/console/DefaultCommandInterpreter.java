package net.johanneslink.scoreboard.console;

import java.util.*;

public class DefaultCommandInterpreter implements CommandInterpreter {

	private final List<Command> registeredCommands;

	DefaultCommandInterpreter(){
		List<Command> registeredCommands = new ArrayList<>();
		registeredCommands.add(new Command(Action.SELECT_A,"Select [A] - Select team A for scoring", "select a", "a"));
		registeredCommands.add(new Command(Action.SELECT_B,"Select [B] - Select team B for scoring",  "select b", "b"));
		registeredCommands.add(new Command(Action.SCORE_1,"Score [1] - Score 1 point for selected team", "score 1", "1"));
		registeredCommands.add(new Command(Action.SCORE_2, "Score [2] - Score 2 points for selected team", "score 2", "2"));
		registeredCommands.add(new Command(Action.SCORE_3, "Score [3] - Score 3 points for selected team", "score 3", "3"));
		registeredCommands.add(new Command(Action.QUIT,"[Q]uit - Terminate the Scoreboard app",  "quit", "q"));
		registeredCommands.add(new Command(Action.HELP, "[?|H]elp - Print this message", "h", "help", "?"));
		this.registeredCommands = registeredCommands;
	}

	@Override
	public Action parse(String line) {
		line = line.toLowerCase();
		for (Command command : registeredCommands) {
			for (String trigger : command.getStrings()) {
				if (trigger.equals(line)) {
					return command.getAction();
				}
			}
		}

		return Action.UNKNOWN;
	}


	public List<Command> getRegisteredCommands() {
		return registeredCommands;
	}

}
