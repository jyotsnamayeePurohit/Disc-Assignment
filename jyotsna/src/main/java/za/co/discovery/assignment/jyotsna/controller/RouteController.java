package za.co.discovery.assignment.jyotsna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.jyotsna.model.Routes;
import za.co.discovery.assignment.jyotsna.service.RouteService;


import java.util.List;
/**
 * This class defines the REST endpoints of CRUD operation for route.
 */
@RestController
@RequestMapping("api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    public List<Routes> getAllRoutes() {
        return routeService.getRoutes();

    }


    @GetMapping("/{id}")
    public Routes getRoutesById(@PathVariable Long id) {
        return routeService.getRoutesById(id);


    }

    @PutMapping("/{id}")
    public Routes updateRoute(@PathVariable Long id, @RequestBody Routes routeDetails) {
        return routeService.updateRoute(id,routeDetails);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoutes(id);
        return ResponseEntity.ok().build();
    }
}






