import java.lang.reflect.Array;
import java.nio.file.FileSystemNotFoundException;
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

        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String str = input.nextLine();
            if (str.equals("bye")) {
                break;
            }
            if (str.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1)+". " + list.get(i));
                }
                System.out.println();
            } else {
                list.add(str);
                System.out.println("added: " + str + "\n");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
