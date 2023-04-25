package atmservice.logic;

import atmservice.model.RequestType;
import atmservice.model.request.ServiceTasks;
import atmservice.model.request.Task;
import atmservice.model.response.ATM;
import atmservice.model.response.Order;

import java.util.*;

public class ATMService {
    private static final int REGION_LIMIT = 9999;
    public static Order process(ServiceTasks serviceTasks) {
        Order response = new Order();
        Map<Integer, Map<RequestType, List<Integer>>> regions = new HashMap<>();

        for(Task task : serviceTasks.getTasks()) {
            Map<RequestType, List<Integer>> priorityMap = regions.computeIfAbsent(task.getRegion(), k -> new HashMap<>());
            var typeArray = priorityMap.computeIfAbsent(task.getRequestType(), k -> new ArrayList<>());
            typeArray.add(task.getAtmId());
        }

        for(int i = 0; i < REGION_LIMIT; i++) {
            if(regions.get(i) == null) continue;

            for(int num : getPriorityArray(regions.get(i))) {
                response.getAtms().add(new ATM(num, i));
            }
        }

        return response;
    }

    private static Set<Integer> getPriorityArray(Map<RequestType, List<Integer>> priorities) {
        Set<Integer> response = new LinkedHashSet<>();

        for (RequestType type : RequestType.values()) {
            List<Integer> priorityArray = priorities.get(type);
            if(priorityArray == null) continue;
            response.addAll(priorityArray);
        }

        return response;
    }
}
