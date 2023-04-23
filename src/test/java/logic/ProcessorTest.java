package logic;

import model.RequestType;
import model.request.ServiceTasks;
import model.request.Task;
import org.junit.Test;

import java.util.List;


public class ProcessorTest {

    @Test
    public void process() {
        // given
        ServiceTasks tasks = new ServiceTasks();
        Task task1 = new Task(1, 1, RequestType.PRIORITY);
        Task task2 = new Task(2, 1, RequestType.SIGNAL_LOW);
        Task task3 = new Task(3, 2, RequestType.STANDARD);
        tasks.setTasks(List.of(task1, task2, task3));

        // when
        var result = Processor.process(tasks);

        // then

    }
}