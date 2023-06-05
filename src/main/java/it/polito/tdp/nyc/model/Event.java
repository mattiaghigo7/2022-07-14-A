package it.polito.tdp.nyc.model;

public class Event implements Comparable<Event>{

	public enum EventType{
		SHARE,
		STOP
	}

	private EventType type;
	private int time;
	private NTA nta;
	private int duration;
	
	public Event(EventType type, int time, NTA nta, int duration) {
		super();
		this.type = type;
		this.time = time;
		this.nta = nta;
		this.duration = duration;
	}

	public EventType getType() {
		return type;
	}

	public int getTime() {
		return time;
	}

	public NTA getNta() {
		return nta;
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public int compareTo(Event o) {
		return this.time-o.time;
	}
}
