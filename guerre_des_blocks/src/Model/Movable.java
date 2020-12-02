package Model;

public interface Movable {
	void movableAttach(MovableObserver to);
	void movableNotifyObserver();
	void movableDisattach (MovableObserver to);
}
