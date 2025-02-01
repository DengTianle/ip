import java.util.Scanner;

public class Ui {
    TaskList list;
    String logo = " ZZZZZ    AAAAA   ZZZZZ   U   U\n" +
            "    Z    A     A     Z    U   U\n" +
            "   Z     AAAAAAA    Z     U   U\n" +
            "  Z      A     A   Z      U   U\n" +
            " ZZZZZ   A     A  ZZZZZ   UUUUU";
    Scanner input;

    public Ui(TaskList list) {
        this.list = list;
        this.input = new Scanner(System.in);
    }

    public String readInput() {
        return input.nextLine();
    }

    public void printWelcome() {
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?\n");
    }
    public void printList() throws InvalidIndexException{
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println((i + 1) + "." + list.getTask(i).toString());
        }
        System.out.println();
    }

    public void printTask(Task task){
        System.out.println("\t" + task.toString());
    }

    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        this.printTask(task);
        System.out.println();
    }

    public void printDelete(Task task)  {
        System.out.println("Nice! I've deleted this task:");
        this.printTask(task);
        System.out.println();
    }

    public void printAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        this.printTask(task);
        System.out.println("Now you have " + list.getSize() + " tasks in the list.\n");
    }
}
