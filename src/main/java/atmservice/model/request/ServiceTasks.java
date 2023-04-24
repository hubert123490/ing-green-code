package atmservice.model.request;

import java.util.ArrayList;
import java.util.List;

public class ServiceTasks {
    private List<Task> tasks = new ArrayList<>();

    public ServiceTasks() {}

    public ServiceTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
