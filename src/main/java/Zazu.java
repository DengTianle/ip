import java.util.ArrayList;
import java.util.Scanner;

public class Zazu {
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
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String logo =
            " ZZZZZ    AAAAA   ZZZZZ   U   U\n" +
            "    Z    A     A     Z    U   U\n" +
            "   Z     AAAAAAA    Z     U   U\n" +
            "  Z      A     A   Z      U   U\n" +
            " ZZZZZ   A     A  ZZZZZ   UUUUU";

        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?\n");

        //ArrayList<Task> list = new ArrayList<>();
        ArrayList<Task> list = FileHandler.loadTasks();

        while (true) {
            try {
                String str = input.nextLine();
                String[] words = str.split(" ");

                if (str.equals("bye")) {
                    break;
                }
                if (str.equals("list")) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                    }
                    System.out.println();
                } else if (words[0].equals("mark")) {
                    if (words.length < 2) {
                        throw new IncompleteCommandException("please enter an index. ");
                    }
                    int index = Integer.parseInt(words[1]) - 1;
                    if (index < 0 || index >= list.size()) {
                        throw new InvalidIndexException();
                    }
                    list.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + list.get(index).toString() + "\n");
                } else if (words[0].equals("delete")) {
                    if (words.length < 2) {
                        throw new IncompleteCommandException("please enter an index. ");
                    }
                    int index = Integer.parseInt(words[1]) - 1;
                    if (index < 0 || index >= list.size()) {
                        throw new InvalidIndexException();
                    }
                    System.out.println("Nice! I've deleted this task:");
                    System.out.println("\t" + list.get(index).toString() + "\n");
                    list.remove(index);
                } else if (words[0].equals("todo")) {
                    String description = Zazu.join(words, 1, words.length);
                    if (description.trim().isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    list.add(new Todo(description));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.\n");
                } else if (words[0].equals("deadline")) {
                    int byIndex = -1;
                    for (int i = 0; i < words.length; i++) {
                        if (words[i].equals("/by")) {
                            byIndex = i;
                        }
                    }
                    if (byIndex == -1) {
                        throw new IncompleteCommandException("please indicate /by. ");
                    }
                    String byStr = Zazu.join(words, byIndex + 1, words.length);
                    if (byStr.trim().isEmpty()) {
                        throw new IncompleteCommandException("please enter an nonempty timing. ");
                    }
                    String description = Zazu.join(words, 1, byIndex);
                    if (description.trim().isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    list.add(new Deadline(description, byStr));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.\n");
                } else if (words[0].equals("event")) {

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
                    String fromStr = Zazu.join(words, fromIndex + 1, toIndex);
                    String toStr = Zazu.join(words, toIndex + 1, words.length);
                    if (fromStr.trim().isEmpty() || toStr.trim().isEmpty()) {
                        throw new IncompleteCommandException("please enter non-empty timings. ");
                    }
                    String description = Zazu.join(words, 1, fromIndex);
                    if (description.trim().isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    list.add(new Event(description, fromStr, toStr));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.\n");
                } else {
                    //list.add(new Task(str));
                    //System.out.println("added: " + str + "\n");
                    System.out.println("Please enter a known command. \n");
                }
            } catch (InvalidIndexException | EmptyDescriptionException | IncompleteCommandException e) {
                System.err.println(e.getMessage()+"\n");
            } catch (Exception e) {
                System.err.println("Error: please check your input and try again. \n");
            }
        }

        FileHandler.saveTasks(list);
        System.out.println("Bye. Hope to see you again soon!");
    }
}

//errors: empty description, time; unknown command; mark out of range; missing /by /from /to