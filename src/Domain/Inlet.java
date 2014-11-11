package Domain;


import Domain.Node;
import Domain.Conveyor;

public class Inlet extends Node {
	private Float _speedMax;
	private boolean _free;
	private Conveyor _conveyor;
	private Node _parentNode;

	public void IsFree() {
		throw new UnsupportedOperationException();
	}
}