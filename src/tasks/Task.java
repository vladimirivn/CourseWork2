/**
 *  Родительский клас описывающий задачу
 */
package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Repeatable {

    private static int idGenerator = 1;
    private String title;
    private TaskType type;
    private int id;
    private LocalDateTime dateTimeOfCompletion;

    private String description;

    public Task(String title, TaskType type, LocalDateTime dateTimeOfCompletion, String description) throws IncorrectArgumentException {
        this.id = idGenerator++;
        setTitle(title);
        setType(type);
        this.dateTimeOfCompletion = dateTimeOfCompletion;
        setDescription(description);
    }

    public abstract boolean appearsIn(LocalDate date);

    public int getId() {
        return id;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) throws IncorrectArgumentException {
        if (type == null) {
            throw new IncorrectArgumentException("Введите корректное название типа задачи");
        } else {
            this.type = type;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IncorrectArgumentException {
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new IncorrectArgumentException("Введите корректное название задачи");
        } else {
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IncorrectArgumentException("Введите корректное описание задачи");
        } else {
            this.description = description;
        }
    }

    public LocalDateTime getDateTimeOfCompletion() {
        return dateTimeOfCompletion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && type == task.type && Objects.equals(dateTimeOfCompletion, task.dateTimeOfCompletion) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, dateTimeOfCompletion, description);
    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", Задача: " + getTitle() + ", Описание: " + getDescription() +
                ", Тип: " + getType() + ", Дата создания: " + getDateTimeOfCompletion();
    }
}
