package logic;

import model.RequestType;
import model.request.ServiceTasks;
import model.request.Task;

import java.util.*;

public class Processor {
    public static Map<Integer, Map<RequestType, List<Integer>>> process(ServiceTasks serviceTasks) {
        Map<Integer, Map<RequestType, List<Integer>>> regions = new HashMap<>();

        for(Task task : serviceTasks.getTasks()) {
            Map<RequestType, List<Integer>> priorityMap = regions.computeIfAbsent(task.getRegion(), k -> new HashMap<>());
            var typeArray = priorityMap.computeIfAbsent(task.getRequestType(), k -> new ArrayList<>());
            typeArray.add(task.getAtmId());
        }

        return regions;
    }
}
