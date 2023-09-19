package za.co.discovery.assignment.jyotsna.model;

import lombok.Data;

@Data
public class Edge {

	private String destination;
	private double distance;

	public Edge(String destination, double distance) {
		super();
		this.destination = destination;
		this.distance = distance;
	}

}
