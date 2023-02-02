/**
 * Класс описывающий ежегодную задачу
 */
package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{
    public YearlyTask(String title, TaskType type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, dateTime, description);
    }

    @Override
    public LocalDateTime getNextDayTimeTask(LocalDateTime localDateTime) {
        return localDateTime.plusYears(1);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(getDateTimeOfCompletion().toLocalDate()) ||
                localDate.isEqual(getDateTimeOfCompletion().toLocalDate()) &&
                        localDate.getDayOfYear() == getDateTimeOfCompletion().getDayOfYear();
    }
}
