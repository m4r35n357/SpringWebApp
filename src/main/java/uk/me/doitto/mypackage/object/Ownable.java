package uk.me.doitto.mypackage.object;

public abstract class Ownable extends PersistentClass {
	
	protected String ownerId;
	
	public String getOwnerId () {
		synchronized (this) {
			return ownerId;
		}
	}

	public void setOwnerId (String ownerId) {
		synchronized (this) {
			this.ownerId = ownerId;
		}
	}
}
