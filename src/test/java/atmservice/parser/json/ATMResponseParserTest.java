package atmservice.parser.json;

import atmservice.model.response.ATM;
import atmservice.model.response.Order;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ATMResponseParserTest {

    @Test
    public void parseResponseWorks() {
        // given
        Order order = new Order(List.of(
                new ATM(1, 1),
                new ATM(1, 2),
                new ATM(2, 3),
                new ATM(1, 3),
                new ATM(1, 4),
                new ATM(1, 5),
                new ATM(2, 5)
                ));
        String expectedResult = "[{\"region\":1,\"atmId\":1},{\"region\":2,\"atmId\":1},{\"region\":3,\"atmId\":2},{\"region\":3,\"atmId\":1},{\"region\":4,\"atmId\":1},{\"region\":5,\"atmId\":1},{\"region\":5,\"atmId\":2}]";

        // when
        String result = ATMResponseParser.parseResponse(order);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}