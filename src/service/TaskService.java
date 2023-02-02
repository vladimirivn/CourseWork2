package service;

import exception.IncorrectArgumentException;
import exception.TaskNotFoundException;
import tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskService {

    private final Map<Integer, Task> taskMap = new HashMap<>();
    private ArrayList<Task> removedTasks = new ArrayList<>();

    public void add(Task task) {
        this.taskMap.put(task.getId(), task);
    }

    public void remove(int id) throws TaskNotFoundException {
        if (this.taskMap.containsKey(id)) {
            removedTasks.add(taskMap.get(id));
            this.taskMap.remove(id);
            System.out.println("Задача №" + id + " удалена");
        } else {
            throw new TaskNotFoundException("Задача №" + id + " не найдена!");
        }
    }
    public Task getTaskId(int id) throws IncorrectArgumentException {
        if (taskMap.containsKey(id)) {
            return taskMap.get(id);
        } else {
            throw new IncorrectArgumentException("Задача №" + id + " не найдена!");
        }
    }
    public Collection<Task> getAllByDate(LocalDate date){

        List<Task> tasksByDay = new LinkedList<>();
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            if (entry.getValue().appearsIn(date)) {
                tasksByDay.add(entry.getValue());
            }
        }
          return tasksByDay;
    }

    public Map<LocalDate, List<Task>> getAllSortedByDate() {
        Map<LocalDate, List<Task>> tasksSortedByDate = new TreeMap<>();
        for (Task task : taskMap.values()) {
            if (!tasksSortedByDate.containsKey(task.getDateTimeOfCompletion().toLocalDate())) {
                tasksSortedByDate.put(task.getDateTimeOfCompletion().toLocalDate(), new ArrayList<>());
            }
            tasksSortedByDate.get(task.getDateTimeOfCompletion().toLocalDate()).add(task);
        }
        return tasksSortedByDate;
    }
    public ArrayList<Task> getRemovedTasks() {
        return removedTasks;
    }
}
