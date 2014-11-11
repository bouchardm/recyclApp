package Domain;


import Domain.Node;
import Domain.Conveyor;

public class Outlet extends Node {
	private Conveyor _conveyor;
	private Node _parentNode;
	private MatterBasket _matterBasket;

	public void IsFree() {
		throw new UnsupportedOperationException();
	}
}