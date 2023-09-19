package za.co.discovery.assignment.jyotsna;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.co.discovery.assignment.jyotsna.controller.RouteController;
import za.co.discovery.assignment.jyotsna.model.Routes;
import za.co.discovery.assignment.jyotsna.service.RouteService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RouteControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRoutes() {
        List<Routes> routesList = new ArrayList<>();
        // Add routes to the list
        when(routeService.getRoutes()).thenReturn(routesList);

        List<Routes> result = routeController.getAllRoutes();

        assertEquals(routesList, result);
    }

    @Test
    public void testGetRoutesById() {
        Long routeId = 1L;
        Routes route = new Routes(); // Create a sample route
        when(routeService.getRoutesById(routeId)).thenReturn(route);

        Routes result = routeController.getRoutesById(routeId);

        assertEquals(route, result);
    }

    @Test
    public void testUpdateRoute() {
        Long routeId = 1L;
        Routes routeDetails = new Routes(); // Create a sample route details
        Routes updatedRoute = new Routes(); // Create an updated route
        when(routeService.updateRoute(routeId, routeDetails)).thenReturn(updatedRoute);

        Routes result = routeController.updateRoute(routeId, routeDetails);

        assertEquals(updatedRoute, result);
    }

    @Test
    public void testDeleteRoute() {
        Long routeId = 1L;
        ResponseEntity<?> response = routeController.deleteRoute(routeId);

        verify(routeService, times(1)).deleteRoutes(routeId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

