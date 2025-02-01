public class Parser {
    public final static String BYE = "bye";
    public final static String LIST = "list";
    public final static String MARK = "mark";
    public final static String DELETE = "delete";
    public final static String TODO = "todo";
    public final static String DEADLINE = "deadline";
    public final static String EVENT = "event";

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
        } else {
            throw new UnknownCommandException();
        }
    }

    public static int parseIndex(String str) throws IncompleteCommandException {
        String[] words = str.split(" ");
        if (words.length < 2) {
            throw new IncompleteCommandException("please enter an index. ");
        }
        return Integer.parseInt(words[1]) - 1;
    }

    public static String parseDescription(String str) throws EmptyDescriptionException {
        String[] words = str.split(" ");
        return Parser.parseDescription(words, words.length);
    }

    public static String parseDescription(String[] words, int end) throws EmptyDescriptionException {
        String description = Parser.join(words, 1, end);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return description;
    }

    public static String[] parseDeadline(String str) throws IncompleteCommandException, EmptyDescriptionException {
        String[] result = new String[2];
        int byIndex = -1;
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                byIndex = i;
            }
        }
        if (byIndex == -1) {
            throw new IncompleteCommandException("please indicate /by. ");
        }
        result[0] = Parser.parseDescription(words, byIndex);
        result[1] = Parser.join(words, byIndex + 1, words.length);
        return result;
    }

    public static String[] parseEvent(String str) throws IncompleteCommandException, EmptyDescriptionException {
        String[] result = new String[3];
        String[] words = str.split(" ");
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/from")) {
                fromIndex = i;
            } else if (words[i].equals("/to")) {
                toIndex = i;
            }
        }
        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            throw new IncompleteCommandException("please use both /from and /to commands in the correct order. ");
        }
        result[1] = Parser.join(words, fromIndex + 1, toIndex);
        result[2] = Parser.join(words, toIndex + 1, words.length);
        result[0] = Parser.parseDescription(words, fromIndex);
        return result;
    }
}
