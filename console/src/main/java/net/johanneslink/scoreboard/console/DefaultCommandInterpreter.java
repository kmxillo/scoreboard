package net.johanneslink.scoreboard.console;

import java.util.*;

import net.johanneslink.scoreboard.core.*;

public class DefaultCommandInterpreter implements CommandInterpreter {

	private final List<Command> registeredCommands;

	DefaultCommandInterpreter(){
		List<Command> registeredCommands = new ArrayList<>();
		registeredCommands.add(new Command(Action.SELECT_A,"Select [A] - Select team A for scoring", "select a", "a"){
			@Override
			void execute(ScoreboardPresenter presenter, Console console) {
				presenter.select(Team.A);
			}
		})
		;
		registeredCommands.add(new Command(Action.SELECT_B,"Select [B] - Select team B for scoring",  "select b", "b"){
			@Override
			void execute(ScoreboardPresenter presenter, Console console) {
				presenter.select(Team.B);
			}
		});
		registeredCommands.add(new Command(Action.SCORE_1,"Score [1] - Score 1 point for selected team", "score 1", "1"){
			@Override
			void execute(ScoreboardPresenter presenter, Console console) {
				presenter.score(Points.One);
			}
		});
		registeredCommands.add(new Command(Action.SCORE_2, "Score [2] - Score 2 points for selected team", "score 2", "2"){
			@Override
			void execute(ScoreboardPresenter presenter, Console console) {
				presenter.score(Points.Two);
			}
		});
		registeredCommands.add(new Command(Action.SCORE_3, "Score [3] - Score 3 points for selected team", "score 3", "3"){
			@Override
			void execute(ScoreboardPresenter presenter, Console console) {
				presenter.score(Points.Three);
			}
		});
		registeredCommands.add(new Command(Action.PLUS, "[+] - Correct Score by +1", "+"){
			@Override
			void execute(ScoreboardPresenter presenter, Console console) {
				presenter.plus();
			}
		});

		registeredCommands.add(new Command(Action.MINUS, "[-] - Correct Score by -1", "-"){
			@Override
			void execute(ScoreboardPresenter presenter, Console console) {
				presenter.minus();
			}
		});


		registeredCommands.add(new Command(Action.QUIT,"[Q]uit - Terminate the Scoreboard app",  "quit", "q"));

		registeredCommands.add(new Command(Action.HELP, "[?|H]elp - Print this message", "h", "help", "?"){
			@Override
			void execute(ScoreboardPresenter presenter, Console console) {
				printHelpString(console);
			}
		});
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



	private void printHelpString(Console console) {
		console.println("Possible commands:");
		for (Command command: registeredCommands){
			console.println(command.getHelpText());
		}
	}


	public List<Command> getRegisteredCommands() {
		return registeredCommands;
	}


}
