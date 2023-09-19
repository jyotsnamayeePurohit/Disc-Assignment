package za.co.discovery.assignment.jyotsna.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Routes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long routeId;
	private String planetOrigin;
	private String planetDestination;
	double distance;

	public Routes(String planetOrigin, String planetDestination, double distance) {
		super();
		this.planetOrigin = planetOrigin;
		this.planetDestination = planetDestination;
		this.distance = distance;
	}

}
