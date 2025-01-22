import java.util.ArrayList;
import java.util.Scanner;

public class Zazu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String logo =
            "  ZZZZZ   AAAAA   ZZZZZ   U   U\n" +
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
                    System.out.println((i + 1) + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
                }
                System.out.println();
            } else if (words[0].equals("mark")) {
                int index = Integer.parseInt(words[1])-1;
                list.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println((index + 1) + ".[" + list.get(index).getStatusIcon() + "] " + list.get(index).getDescription() + "\n");
            } else {
                list.add(new Task(str));
                System.out.println("added: " + str + "\n");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
