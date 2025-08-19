class TaskNode {
    String taskName;
    int priority;
    TaskNode next;
    TaskNode prev;
    TaskNode(String taskName, int priority) {
        this.taskName = taskName;
        this.priority = priority;
        this.next = null;
        this.prev = null;
    }
}
abstract class AbstractTasklist {
    abstract void addTask(String taskName, int priority);
    abstract void removeTask(String taskName);
    abstract void displayTasks();
    abstract void showTasks();
}
class DoubleLinkedList extends AbstractTasklist {
    private TaskNode head;
    private TaskNode tail;
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }
    @Override
    public void addTask(String taskName, int priority) {
        TaskNode newNode = new TaskNode(taskName, priority);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    @Override
    public void removeTask(String taskName) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskName.equals(taskName)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }
    @Override
    public void displayTasks() {
        TaskNode current = head;
        while (current != null) {
            System.out.println("Task: " + current.taskName + ", Priority: " + current.priority);
            current = current.next;
        }
    }
    @Override
    public void showTasks() {
        TaskNode temp = head;
        while (temp != null) {
            System.out.println("Task: " + temp.taskName + ", Priority: " + temp.priority);
            temp = temp.next;
        }
    }
}
public class TaskManager {
    public static void main(String[] args) {
        AbstractTasklist tasklist = new DoubleLinkedList();
        tasklist.addTask("design module", 2);
        tasklist.addTask("Testing", 1);
        tasklist.addTask("deployment", 3);

        tasklist.showTasks();
    }
}
