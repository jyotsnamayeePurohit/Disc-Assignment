package za.co.discovery.assignment.jyotsna.service;

import za.co.discovery.assignment.jyotsna.model.Traffic;
import java.util.List;

public interface TrafficService {
    public List<Traffic> getTraffic();
    public Traffic updateTraffic(Long id, Traffic trafficDetails);
    public Traffic getTrafficById(Long id);
    public void deleteTrafficById(Long id);
}
