package net.johanneslink.scoreboard.it;

import java.io.*;
import java.util.*;
import java.util.List;

import net.johanneslink.scoreboard.console.Main;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

	private ByteArrayOutputStream stdout;
	private PrintStream originalStdout;

	private ByteArrayInputStream stdin;
	private InputStream originalStdin;

	@BeforeEach
	void init() {
		originalStdin = System.in;
		stdout = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stdout);
		originalStdout = System.out;
		System.setOut(ps);

	}

	@AfterEach
	void reset() {
		System.setOut(originalStdout);
		System.setIn(originalStdin);
	}

	@Test
	void startingConsoleAppDisplaysInitialScore() throws Exception {
		String inputText = String.format("Q%n");
		stdin = new ByteArrayInputStream(inputText.getBytes());
		System.setIn(stdin);

		startConsoleApp();
		List<String> stdoutLines = stdoutLines();
		assertEquals(1, stdoutLines.size());
		assertEquals("000:000", stdoutLines.get(0));
	}

	@Test
	void consoleAppInterpretsInputLineByLineAndCanBeQuit() throws InterruptedException, IOException {
		setConsoleInput("A", "1", "B", "1", "2", "3", "4", "Q");
		startConsoleApp();

		List<String> stdoutLines = stdoutLines();
		Assertions.assertAll(//
				() -> assertEquals(8, stdoutLines.size()), //
				() -> assertEquals("Team A selected", stdoutLines.get(1)), //
				() -> assertEquals("001:000", stdoutLines.get(2)), //
				() -> assertEquals("Team B selected", stdoutLines.get(3)), //
				() -> assertEquals("001:001", stdoutLines.get(4)), //
				() -> assertEquals("001:003", stdoutLines.get(5)), //
				() -> assertEquals("001:006", stdoutLines.get(6)), //
				() -> assertEquals("Unknown command '4'", stdoutLines.get(7)) //
		);
	}

	private void setConsoleInput(String... commands) {

		StringBuilder stringBuilder = new StringBuilder();
		for (String command : commands) {
			stringBuilder.append(command);
			stringBuilder.append(System.getProperty("line.separator"));
		}

		stringBuilder.append("Q");
		stringBuilder.append(System.getProperty("line.separator"));

		stdin = new ByteArrayInputStream(stringBuilder.toString().getBytes());
		System.setIn(stdin);
	}

	private void startConsoleApp(final String... args) throws InterruptedException {
		Thread mainThread = new Thread(() -> Main.main(args));
		mainThread.start();
		mainThread.join(2000);
	}

	private List<String> stdoutLines() throws IOException {
		stdout.flush();
		String[] lines = stdout.toString().trim().split(System.getProperty("line.separator"));
		return Arrays.asList(lines);
	}
}
