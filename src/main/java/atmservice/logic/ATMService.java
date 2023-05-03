package atmservice.logic;

import atmservice.model.request.RequestType;
import atmservice.model.request.ServiceTasks;
import atmservice.model.request.Task;
import atmservice.model.response.ATM;
import atmservice.model.response.Order;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ATMService {

    public static Order getOrderFromServiceTasks(ServiceTasks serviceTasks) {
        Map<Integer, Map<RequestType, List<ATM>>> regionMap = getRegionRequestsByRequestType.apply(serviceTasks);
        return new Order(getSortedATMs.apply(regionMap));
    }

    private static final Function<ServiceTasks, Map<Integer, Map<RequestType, List<ATM>>>> getRegionRequestsByRequestType = serviceTasks -> (
            serviceTasks.getTasks().parallelStream()
                    .collect(Collectors.groupingBy(
                            Task::getRegion,
                            Collectors.groupingBy(
                                    Task::getRequestType,
                                    Collectors.mapping(ATM::new, Collectors.toList())
                            )
                    ))
    );

    private static final Function<Map<RequestType, List<ATM>>, List<ATM>> getDistinctOrderedATMsByRegion = regionRequestMap -> (
            Arrays.stream(RequestType.values())
                    .flatMap(type -> regionRequestMap.containsKey(type) ? regionRequestMap.get(type).stream() : Stream.empty())
                    .distinct()
                    .collect(Collectors.toList())
    );

    private static final Function<Map<Integer, Map<RequestType, List<ATM>>>, List<ATM>> getSortedATMs = regions -> (
            regions.entrySet().parallelStream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue).map(getDistinctOrderedATMsByRegion)
                    .flatMap(List::stream)
                    .collect(Collectors.toList())
    );
}
