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

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
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
                int index = Integer.parseInt(words[1]) - 1;
                list.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + list.get(index).toString() + "\n");
            } else if (words[0].equals("todo")) {
                list.add(new Todo(Zazu.join(words, 1, words.length)));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + list.get(list.size()-1));
                System.out.println("Now you have " + list.size() + " tasks in the list.\n");
            } else if (words[0].equals("deadline")) {
                int byIndex = -1;
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals("/by")) {
                        byIndex = i;
                    }
                }
                String byStr = Zazu.join(words, byIndex+1, words.length);
                list.add(new Deadline(Zazu.join(words, 1, byIndex), byStr));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + list.get(list.size()-1));
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
                String fromStr = Zazu.join(words, fromIndex+1, toIndex);
                String toStr = Zazu.join(words, toIndex+1, words.length);
                list.add(new Event(Zazu.join(words, 1, fromIndex), fromStr, toStr));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + list.get(list.size()-1));
                System.out.println("Now you have " + list.size() + " tasks in the list.\n");
            } else {
                list.add(new Task(str));
                System.out.println("added: " + str + "\n");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
