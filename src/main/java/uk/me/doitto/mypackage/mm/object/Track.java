package uk.me.doitto.mypackage.mm.object;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import uk.me.doitto.mypackage.globals.Constants;
import uk.me.doitto.mypackage.object.Ownable;
import uk.me.doitto.mypackage.object.PersistentClass;

/**
 * Class representing a single track
 * 
 * @author Ian Smith
 *
 */
public class Track extends Ownable implements Serializable, Comparable<PersistentClass> {
	
	private static final long serialVersionUID = 2064392335951522059L;

	private int duration;
	
	private String url;

	private Date date;
	
	private transient String[] linkAlbumIds;

	private Set<Album> albums = Collections.synchronizedSet(new HashSet<Album>());

	private transient String[] linkPerformerIds;

	private Set<Artist> performers = Collections.synchronizedSet(new HashSet<Artist>());
	
	// for hibernate
	public Track() {
	}
	// for searching
	public Track(String title) {
		this();
		this.name = title;
	}
	// for normal creation
	public Track (String title, String ownerId) {
		this(title);
		this.ownerId = ownerId;
	}

	public String getTitle() {
		return name;
	}

	public void setTitle(String title) {
		this.name = title;
	}

	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String[] getLinkAlbumIds() {
		return linkAlbumIds;
	}

	public void setLinkAlbumIds(String[] linkAlbumIds) {
		this.linkAlbumIds = linkAlbumIds;
	}

	public String[] getLinkPerformerIds() {
		return linkPerformerIds;
	}

	public void setLinkPerformerIds(String[] linkPerformerIds) {
		this.linkPerformerIds = linkPerformerIds;
	}

	public Set<Album> getAlbums () {
		return albums;
	}
	public void setAlbums (Set<Album> albums) {
		this.albums = albums;
	}

	public Set<Artist> getPerformers () {
		return performers;
	}
	public void setPerformers (Set<Artist> performers) {
		this.performers = performers;
	}
	
	// Associations, convenience methods
	public void addToPerformers(Artist performer) {
		this.getPerformers().add(performer);
		performer.getPerformances().add(this);
	}

	public void removeFromPerformers(Artist performer) {
		this.getPerformers().remove(performer);
		performer.getPerformances().remove(this);
	}
	
	public String toString () {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Title: ");
		stringBuilder.append(name);
		if (url != null) {
			stringBuilder.append(", URL: ");
			stringBuilder.append(url);
		}
		if (date != null) {
			stringBuilder.append(", Date:  ");
			stringBuilder.append(date.toString());
		}
		return stringBuilder.toString();
	}
	
	public String getDetails () {
		StringBuilder stringBuilder = new StringBuilder();
		if (!albums.isEmpty()) {
			stringBuilder.append(" - Albums - ");
			stringBuilder.append(Constants.lineSeparator);
			for (Album album : albums) {
				stringBuilder.append(album.getTitle());
				stringBuilder.append(Constants.lineSeparator);
			}
		}
		if (!performers.isEmpty()) {
			stringBuilder.append(" - Performers - ");
			stringBuilder.append(Constants.lineSeparator);
			for (Artist performer : performers) {
				stringBuilder.append(performer.getName());
				stringBuilder.append(Constants.lineSeparator);
			}
		}
		return stringBuilder.toString();
	}
	
	public boolean isLinked () {
		return (! getAlbums().isEmpty()) || (! getPerformers().isEmpty());
	}
}