package com.md.dm.vi.tp.entity;

public class Population {

	private String zoneName;
	private Integer density;
	private Integer commutingDensity;
	
	public Population(String zoneName, Integer density, Integer commutingDensity) {
		super();
		this.zoneName = zoneName;
		this.density = density;
		this.commutingDensity = commutingDensity;
	}

	public String getZoneName() {
		return zoneName;
	}

	public Integer getDensity() {
		return density;
	}

	public Integer getCommutingDensity() {
		return commutingDensity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((commutingDensity == null) ? 0 : commutingDensity.hashCode());
		result = prime * result + ((density == null) ? 0 : density.hashCode());
		result = prime * result
				+ ((zoneName == null) ? 0 : zoneName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Population other = (Population) obj;
		if (commutingDensity == null) {
			if (other.commutingDensity != null)
				return false;
		} else if (!commutingDensity.equals(other.commutingDensity))
			return false;
		if (density == null) {
			if (other.density != null)
				return false;
		} else if (!density.equals(other.density))
			return false;
		if (zoneName == null) {
			if (other.zoneName != null)
				return false;
		} else if (!zoneName.equals(other.zoneName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Population [zoneName=" + zoneName + ", density=" + density
				+ ", commutingDensity=" + commutingDensity + "]";
	}
	
	
}
