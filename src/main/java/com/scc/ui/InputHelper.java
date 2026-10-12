package com.scc.ui;

import java.util.Scanner;

/**
 * Shared, error-tolerant keyboard input. Every method keeps asking until the
 * user gives a valid answer, so typos never crash the program.
 */
public final class InputHelper {

    private static final Scanner SC = new Scanner(System.in);

    private InputHelper() {
    }

    /** Reads a non-empty line (extra spaces are trimmed). */
    public static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!SC.hasNextLine()) {            // Ctrl+D / closed input
                System.out.println("\nInput closed. Exiting...");
                System.exit(0);
            }
            String line = SC.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("  [!] This field cannot be empty. Please try again.");
        }
    }

    /** Reads a whole number between min and max (inclusive). */
    public static int readChoice(String prompt, int min, int max) {
        while (true) {
            String line = readText(prompt);
            try {
                int n = Integer.parseInt(line);
                if (n >= min && n <= max) {
                    return n;
                }
                System.out.println("  [!] Please enter a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  [!] '" + line + "' is not a number. Please enter " + min + "-" + max + ".");
            }
        }
    }

    /** Yes/No question. Accepts y, yes, n, no in any letter case. */
    public static boolean confirm(String prompt) {
        while (true) {
            String a = readText(prompt + " (y/n): ").toLowerCase();
            if (a.equals("y") || a.equals("yes")) return true;
            if (a.equals("n") || a.equals("no")) return false;
            System.out.println("  [!] Please type y or n.");
        }
    }

    public static void pause() {
        System.out.print("\nPress Enter to continue...");
        if (SC.hasNextLine()) SC.nextLine();
    }
}
