package it.polito.tdp.nyc.model;

import java.util.Objects;
import java.util.Set;

public class NTA {

	private String NTACode;
	private Set<String> SSIDs;
	
	public NTA(String nTACode, Set<String> sSIDs) {
		super();
		NTACode = nTACode;
		SSIDs = sSIDs;
	}

	public String getNTACode() {
		return NTACode;
	}

	public void setNTACode(String nTACode) {
		NTACode = nTACode;
	}

	public Set<String> getSSIDs() {
		return SSIDs;
	}

	public void setSSIDs(Set<String> sSIDs) {
		SSIDs = sSIDs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(NTACode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NTA other = (NTA) obj;
		return Objects.equals(NTACode, other.NTACode);
	}

	@Override
	public String toString() {
		return "NTA [NTACode=" + NTACode + ", SSIDs=" + SSIDs + "]";
	}
	
}
