package uk.me.doitto.mypackage.object;

/**
 * Base class providing a consistent approach to storage and retrieval via the web or web services, using a String object ID
 * @author ian
 *
 */
public abstract class PersistentClass implements Comparable<PersistentClass> {
	
	protected String id;
	
	protected int version;
	
	protected String name;
	
	public synchronized String getId () {
		return id;
	}

	public synchronized void setId (String id) {
		this.id = id;
	}

	public synchronized int getVersion () {
		return version;
	}

	public synchronized void setVersion (int version) {
		this.version = version;
	}

	public synchronized String getName () {
		return name;
	}

	public synchronized void setName (String name) {
		this.name = name;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PersistentClass))
			return false;
		final PersistentClass other = (PersistentClass) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public int compareTo (PersistentClass other) {
		if (this.equals(other)) {
			return 0;
		} else {
			return name.compareToIgnoreCase(other.getName());
		}
	}
}
