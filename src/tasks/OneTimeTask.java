/**
 * Класс описывающий одноразовую задачу
 */
package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, TaskType type, LocalDateTime dateTime, String description) throws IncorrectArgumentException {
        super(title, type, dateTime, description);
    }

    @Override
    public LocalDateTime getNextDayTimeTask(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return date.equals(getDateTimeOfCompletion().toLocalDate());
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
