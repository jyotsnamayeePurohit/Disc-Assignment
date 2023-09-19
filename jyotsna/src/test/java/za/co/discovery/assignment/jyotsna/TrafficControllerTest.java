package za.co.discovery.assignment.jyotsna;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.co.discovery.assignment.jyotsna.controller.TrafficController;
import za.co.discovery.assignment.jyotsna.model.Traffic;
import za.co.discovery.assignment.jyotsna.service.TrafficService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrafficControllerTest {

    @Mock
    private TrafficService trafficService;

    @InjectMocks
    private TrafficController trafficController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTraffic() {
        List<Traffic> trafficList = new ArrayList<>();
        // Add traffic data to the list
        when(trafficService.getTraffic()).thenReturn(trafficList);

        List<Traffic> result = trafficController.getAllTraffic();

        assertEquals(trafficList, result);
    }

    @Test
    public void testGetTrafficById() {
        Long trafficId = 1L;
        Traffic traffic = new Traffic(); // Create a sample traffic data
        when(trafficService.getTrafficById(trafficId)).thenReturn(traffic);

        Traffic result = trafficController.getTrafficById(trafficId);

        assertEquals(traffic, result);
    }

    @Test
    public void testUpdateTraffic() {
        Long trafficId = 1L;
        Traffic trafficDetails = new Traffic(); // Create sample traffic details
        Traffic updatedTraffic = new Traffic(); // Create an updated traffic data
        when(trafficService.updateTraffic(trafficId, trafficDetails)).thenReturn(updatedTraffic);

        Traffic result = trafficController.updateRoute(trafficId, trafficDetails);

        assertEquals(updatedTraffic, result);
    }

    @Test
    public void testDeleteTraffic() {
        Long trafficId = 1L;
        ResponseEntity<?> response = trafficController.deleteTraffic(trafficId);

        verify(trafficService, times(1)).deleteTrafficById(trafficId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

