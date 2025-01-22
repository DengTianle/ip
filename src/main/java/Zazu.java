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

        while (true) {
            String str = input.nextLine();
            if (str.equals("bye")) {
                break;
            }
            System.out.println(str);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
