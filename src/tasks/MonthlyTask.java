/**
 * Класс описывающий ежемесячную задачу
 */
package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(String title, TaskType type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, dateTime, description);
    }

    @Override
    public LocalDateTime getNextDayTimeTask(LocalDateTime localDateTime) {
        return localDateTime.plusMonths(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(getDateTimeOfCompletion().toLocalDate()) ||
                localDate.isEqual(getDateTimeOfCompletion().toLocalDate()) &&
                        localDate.getDayOfMonth() == getDateTimeOfCompletion().getDayOfMonth();
    }
}
