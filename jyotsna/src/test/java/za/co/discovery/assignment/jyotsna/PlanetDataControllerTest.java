package za.co.discovery.assignment.jyotsna;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import za.co.discovery.assignment.jyotsna.controller.PlanetDataController;
import za.co.discovery.assignment.jyotsna.service.PlanetServices;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PlanetDataControllerTest {

	@Mock
	private PlanetServices planetService;

	@InjectMocks
	private PlanetDataController planetDataController;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testLoadPlanetsData_Success() {
		MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test data".getBytes());

		String successMessage = "Data Successfully uploaded into the Database";
		when(planetService.loadPlanetsDataToDataBase(file)).thenReturn(successMessage);

		String result = planetDataController.loadPlanetsData(file);

		assertEquals(successMessage, result);
	}

	@Test
	public void testSubmitForm() {
		String source = "A";
		String destination = "B";
		Map<String, Double> shortestPathResult = new HashMap<>();
		// Add shortest path data to the map
		when(planetService.findShortestPath(source, destination)).thenReturn(shortestPathResult);

		Map<String, Double> result = planetDataController.submitForm(source, destination);

		assertEquals(shortestPathResult, result);
	}
}
