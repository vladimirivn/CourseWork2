/**
 * Обработка проверяемых исключений при создании задачи
 */
package exception;

public class IncorrectArgumentException extends Exception {
    public IncorrectArgumentException(String message) {
        super(message);
    }
}
