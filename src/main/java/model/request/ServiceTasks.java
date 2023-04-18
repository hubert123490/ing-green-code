package model.request;

import java.util.ArrayList;
import java.util.List;

public class ServiceTasks {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
