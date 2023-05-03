package atmservice.model.request;

import java.util.List;
import java.util.Objects;

public class ServiceTasks {
    private final List<Task> tasks;

    public ServiceTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceTasks that = (ServiceTasks) o;
        return Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }
}
