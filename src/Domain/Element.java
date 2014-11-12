package Domain;


import Domain.SortStation;
import Domain.EntryPoint;
import Domain.ExitPoint;
import Domain.Conveyor;

public abstract class Element extends MatterBasket {
	public SortStation _sortStation;
	public EntryPoint _entryPoint;
	public ExitPoint _exitPoint;
	public Conveyor _conveyor;
	public Junction _junction;

	public void isIn() {
		throw new UnsupportedOperationException();
	}
}