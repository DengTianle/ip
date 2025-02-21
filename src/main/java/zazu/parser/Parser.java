package zazu.parser;

import zazu.data.exception.EmptyDescriptionException;
import zazu.data.exception.IncompleteCommandException;
import zazu.data.exception.UnknownCommandException;

/**
 * Utility class for parsing user input commands and extracting relevant information.
 * This class contains methods to identify commands,
 * parse descriptions, indexes, deadlines, and events.
 */
public class Parser {

    /** Command for exiting the program */
    public final static String BYE = "bye";

    /** Command for listing tasks */
    public final static String LIST = "list";

    /** Command for marking a task */
    public final static String MARK = "mark";

    /** Command for deleting a task */
    public final static String DELETE = "delete";

    /** Command for creating a todo task */
    public final static String TODO = "todo";

    /** Command for creating a deadline task */
    public final static String DEADLINE = "deadline";

    /** Command for creating an event task */
    public final static String EVENT = "event";

    /** Command for finding tasks with matching description */
    public final static String FIND = "find";

    /** Keyword to indicate when an event begins */
    public final static String FROM = "/from";

    /** Keyword to indicate when an event ends */
    public final static String TO = "/to";

    /** Keyword to indicate when a deadline is */
    public final static String BY = "/by";

    /**
     * Joins a portion of a string array into a single string.
     *
     * @param sList The array of strings to join.
     * @param start The starting index to begin joining.
     * @param end The ending index (exclusive) to stop joining.
     * @return A single string formed by joining the elements
     * of {@code sList} from index {@code start} to {@code end}.
     */
    public static String join(String[] sList, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(sList[i]);
            if (i != end - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Identifies the command type from a user input string.
     *
     * @param str The input string containing the command.
     * @return A string representing the command type.
     * @throws UnknownCommandException If the command is not recognized.
     */
    public static String identifyCommand(String str) throws UnknownCommandException {
        String[] words = str.split(" ");
        if (str.equals("bye")) {
            return Parser.BYE;
        }
        if (str.equals("list")) {
            return Parser.LIST;
        } else if (words[0].equals("mark")) {
            return Parser.MARK;
        } else if (words[0].equals("delete")) {
            return Parser.DELETE;
        } else if (words[0].equals("todo")) {
            return Parser.TODO;
        } else if (words[0].equals("deadline")) {
            return Parser.DEADLINE;
        } else if (words[0].equals("event")) {
            return Parser.EVENT;
        } else if (words[0].equals("find")) {
            return Parser.FIND;
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the index from the user input string.
     *
     * @param str The input string containing the index.
     * @return The parsed index (adjusted to zero-based indexing).
     * @throws IncompleteCommandException If the index is missing from the command.
     */
    public static int parseIndex(String str) throws IncompleteCommandException {
        String[] words = str.split(" ");
        if (words.length < 2) {
            throw new IncompleteCommandException("please enter an index. ");
        }
        return Integer.parseInt(words[1]) - 1;
    }

    /**
     * Parses the description from the user input string.
     *
     * @param str The input string containing the description.
     * @return The parsed description as a string.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public static String parseDescription(String str) throws EmptyDescriptionException {
        String[] words = str.split(" ");
        return Parser.parseDescription(words, words.length);
    }

    /**
     * Parses the description from an array of words.
     *
     * @param words The array of words to extract the description from.
     * @param end The index up to which the description should be extracted.
     * @return The parsed description as a string.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public static String parseDescription(String[] words, int end) throws EmptyDescriptionException {
        String description = Parser.join(words, 1, end);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return description;
    }

    /**
     * Parses the deadline command and returns the description and deadline.
     *
     * @param str The input string containing the deadline information.
     * @return An array containing the description and the deadline.
     * @throws IncompleteCommandException If the deadline command is incomplete or missing "/by".
     * @throws EmptyDescriptionException If the description is empty.
     */
    public static String[] parseDeadline(String str) throws IncompleteCommandException, EmptyDescriptionException {
        String[] result = new String[2];
        int byIndex = -1;
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(BY)) {
                byIndex = i;
            }
        }
        if (byIndex == -1) {
            throw new IncompleteCommandException("please indicate " + BY + ". ");
        }
        result[0] = Parser.parseDescription(words, byIndex);
        result[1] = Parser.join(words, byIndex + 1, words.length);
        return result;
    }

    /**
     * Parses the event command and returns the description, start time, and end time.
     *
     * @param str The input string containing the event information.
     * @return An array containing the description, start time, and end time.
     * @throws IncompleteCommandException If the event command is incomplete or
     * missing "/from" or "/to".
     * @throws EmptyDescriptionException If the description is empty.
     */
    public static String[] parseEvent(String str) throws IncompleteCommandException, EmptyDescriptionException {
        String[] result = new String[3];
        String[] words = str.split(" ");
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(FROM)) {
                fromIndex = i;
            } else if (words[i].equals(TO)) {
                toIndex = i;
            }
        }
        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            throw new IncompleteCommandException("please use both " + FROM + " and " + TO + " commands in the correct order. ");
        }
        result[1] = Parser.join(words, fromIndex + 1, toIndex);
        result[2] = Parser.join(words, toIndex + 1, words.length);
        result[0] = Parser.parseDescription(words, fromIndex);
        return result;
    }
}
