package net.johanneslink.scoreboard.console;

import java.util.Optional;
import java.util.stream.Stream;

import net.johanneslink.scoreboard.core.*;

public class ScoreboardConsoleApp implements ScoreboardView {

	private Console console;
	private ScoreboardPresenter presenter;
	private CommandInterpreter interpreter;

	ScoreboardConsoleApp(Console console) {
		this.console = console;
	}

	void run(ScoreboardPresenter presenter, CommandInterpreter interpreter) {
		presenter.register(this);
		this.presenter = presenter;
		this.interpreter = interpreter;
		loop(interpreter);
	}

	private void loop(CommandInterpreter interpreter) {
		while (true) {
			String line = console.readLine();
			Action action = interpreter.parse(line);
			if (action == Action.QUIT) break;
			if (action == Action.UNKNOWN) console.println("Unknown command '" + line + "'");
			Optional<Command> first = interpreter.getRegisteredCommands()
					.stream()
					.filter(c -> c.getAction() == action)
					.findFirst();
			first.ifPresent(command -> command.execute(presenter, console));
		}
	}

	@Override
	public void displaySelectedTeam(Team team) {
		if (team == Team.NONE) return;
		console.println("Team " + team.name() + " selected");
	}

	@Override
	public void displayScore(Score score) {
		console.println(score.toString());
	}
}
