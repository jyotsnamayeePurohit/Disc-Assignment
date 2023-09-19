package za.co.discovery.assignment.jyotsna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.jyotsna.model.Traffic;
import za.co.discovery.assignment.jyotsna.service.TrafficService;

import java.util.List;
/**
 * This class defines the REST endpoints of CRUD operation for Traffic.
 */
@RestController
@RequestMapping("api/traffic")
public class TrafficController {


    @Autowired
    private TrafficService trafficService;

   @GetMapping
    public List<Traffic> getAllTraffic() {
        return trafficService.getTraffic();
    }


    @GetMapping("/{id}")
    public Traffic getTrafficById(@PathVariable Long id) {
        return trafficService.getTrafficById(id);
    }

    @PutMapping("/{id}")
    public Traffic updateRoute(@PathVariable Long id, @RequestBody Traffic trafficDetails) {
        return trafficService.updateTraffic(id,trafficDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTraffic(@PathVariable Long id) {

        trafficService.deleteTrafficById(id);

        return ResponseEntity.ok().build();
    }
}

