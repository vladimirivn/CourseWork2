/**
 * Класс описывающий еженедельную задачу
 */
package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, TaskType type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, dateTime, description);
    }

    @Override
    public LocalDateTime getNextDayTimeTask(LocalDateTime localDateTime) {
        return localDateTime.plusWeeks(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(getDateTimeOfCompletion().toLocalDate()) ||
                localDate.isEqual(getDateTimeOfCompletion().toLocalDate()) &&
                localDate.getDayOfWeek() == getDateTimeOfCompletion().getDayOfWeek();
    }
}
