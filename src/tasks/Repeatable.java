/**
 * Получение следующей даты и времени выполнения задачи
 */
package tasks;

import java.time.LocalDateTime;

public interface Repeatable {
    LocalDateTime getNextDayTimeTask(LocalDateTime localDateTime);

}
