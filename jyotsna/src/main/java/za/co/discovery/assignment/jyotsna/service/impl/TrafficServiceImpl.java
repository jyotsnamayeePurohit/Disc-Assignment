package za.co.discovery.assignment.jyotsna.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.jyotsna.exception.ResourceNotFoundException;
import za.co.discovery.assignment.jyotsna.model.Traffic;
import za.co.discovery.assignment.jyotsna.repository.TrafficRepository;
import za.co.discovery.assignment.jyotsna.service.TrafficService;


import java.util.List;

@Service
public class TrafficServiceImpl implements TrafficService {
    @Autowired
    private TrafficRepository trafficRepository;
    @Override
    public List<Traffic> getTraffic() {
        return trafficRepository.findAll();
    }

    @Override
    public Traffic updateTraffic(Long id, Traffic trafficDetails) {
        Traffic traffic = trafficRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Traffic not found with id " + id));

        traffic.setRouteId(trafficDetails.getRouteId());
        traffic.setPlanetDestination(trafficDetails.getPlanetDestination());
        traffic.setPlanetOrgin(trafficDetails.getPlanetOrgin());
        traffic.setTrafficDelay(trafficDetails.getTrafficDelay());

        return trafficRepository.save(traffic);
    }

    @Override
    public Traffic getTrafficById(Long id) {
        return trafficRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Traffic not found with id " + id));
    }


    @Override
    public void deleteTrafficById(Long id) {
        Traffic traffic = trafficRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Traffic not found with id " + id));

        trafficRepository.delete(traffic);
    }
}
