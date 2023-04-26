package atmservice.logic;

import atmservice.model.RequestType;
import atmservice.model.request.ServiceTasks;
import atmservice.model.request.Task;
import atmservice.model.response.ATM;
import atmservice.model.response.Order;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ATMServiceTest {

    @Test
    public void testCase1() {
        // given
        ServiceTasks serviceTasks = new ServiceTasks(List.of(
                new Task(1, 4, RequestType.STANDARD),
                new Task(1, 1, RequestType.STANDARD),
                new Task(1, 2, RequestType.STANDARD),
                new Task(2, 3, RequestType.PRIORITY),
                new Task(1, 3, RequestType.STANDARD),
                new Task(1, 2, RequestType.SIGNAL_LOW),
                new Task(2, 5, RequestType.STANDARD),
                new Task(1, 5, RequestType.FAILURE_RESTART)
        ));
        Order expectedResult = new Order(List.of(
                new ATM(1, 1),
                new ATM(1, 2),
                new ATM(2, 3),
                new ATM(1, 3),
                new ATM(1, 4),
                new ATM(1, 5),
                new ATM(2, 5)
        ));

        // when
        Order result = ATMService.process(serviceTasks);

        // then
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testCase2() {
        // given
        ServiceTasks serviceTasks = new ServiceTasks(List.of(
                new Task(2, 1, RequestType.STANDARD),
                new Task(1, 1, RequestType.STANDARD),
                new Task(3, 2, RequestType.PRIORITY),
                new Task(4, 3, RequestType.STANDARD),
                new Task(5, 4, RequestType.STANDARD),
                new Task(2, 5, RequestType.PRIORITY),
                new Task(1, 5, RequestType.STANDARD),
                new Task(2, 3, RequestType.SIGNAL_LOW),
                new Task(1, 2, RequestType.SIGNAL_LOW),
                new Task(1, 3, RequestType.FAILURE_RESTART)
        ));
        Order expectedResult = new Order(List.of(
                new ATM(2, 1),
                new ATM(1, 1),
                new ATM(3, 2),
                new ATM(1, 2),
                new ATM(1, 3),
                new ATM(2, 3),
                new ATM(4, 3),
                new ATM(5, 4),
                new ATM(2, 5),
                new ATM(1, 5)
        ));

        // when
        Order result = ATMService.process(serviceTasks);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}