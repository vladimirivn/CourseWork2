package tasks;

public enum TaskType {
    WORK("Рабочая"),
    PERSONAL("Личная");

    private final String nameTaskType;

    TaskType(String nameTaskType) {
        this.nameTaskType = nameTaskType;
    }

    @Override
    public String toString() {
        return nameTaskType;
    }
}
