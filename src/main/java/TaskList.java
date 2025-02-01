import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = tasks;
        }
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task deleteTask(int index) throws InvalidIndexException {
        Task task = this.getTask(index);
        list.remove(index);
        return task;
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int index) throws InvalidIndexException {
        if (index < 0 || index >= list.size()) {
            throw new InvalidIndexException();
        }
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
