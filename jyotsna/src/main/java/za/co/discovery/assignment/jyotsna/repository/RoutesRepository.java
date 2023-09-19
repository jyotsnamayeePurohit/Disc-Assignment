package za.co.discovery.assignment.jyotsna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.jyotsna.model.Routes;


import java.util.List;

@Repository
public interface RoutesRepository extends JpaRepository<Routes, Long> {

	List<Routes> findByPlanetOrigin(String destination);
}
