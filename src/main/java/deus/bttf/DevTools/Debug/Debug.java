package deus.bttf.DevTools.Debug;

public class Debug {
	private static final String RESET = "\033[0m";
	private static final String BLUE = "\033[34m";
	private static final String YELLOW = "\033[33m";
	private static final String CYAN = "\033[36m";

	public static boolean isDebug = false;

	public static void println(String x) {
		if (!isDebug) return;
		System.out.println(BLUE + "[Debug]" + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
		System.out.println(CYAN + x + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
	}

	public static void println(int x) {
		if (!isDebug) return;
		System.out.println(BLUE + "[Debug]" + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
		System.out.println(CYAN + x + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
	}

	public static void println(Boolean x) {
		if (!isDebug) return;
		System.out.println(BLUE + "[Debug]" + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
		System.out.println(CYAN + x + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
	}

	public static void println(float x) {
		if (!isDebug) return;
		System.out.println(BLUE + "[Debug]" + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
		System.out.println(CYAN + x + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
	}

	public static void println(double x) {
		if (!isDebug) return;
		System.out.println(BLUE + "[Debug]" + RESET);
		System.out.println(YELLOW + "----------------------------------------------------------" + RESET);
		System.out.println(CYAN + x + RESET);
	}

	public static void debugExecuteIt(Runnable code) {
		if (isDebug)
		{code.run();}
	}
}
