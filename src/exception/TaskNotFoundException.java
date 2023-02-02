/**
 * Обработка проверяемых исключений при поиске задачи
 */
package exception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
