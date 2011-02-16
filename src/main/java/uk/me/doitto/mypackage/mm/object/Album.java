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
 * Class representing a compilation of two or more tracks, eg. an album, single, EP etc.
 * 
 * This class is used both for persistent storage by hibernate, and as a form backing object in the web user interface.
 * 
 * @author Ian Smith
 *
 */
public class Album extends Ownable implements Serializable, Comparable<PersistentClass> {
	
	private static final long serialVersionUID = 7171381182182673129L;

	private String label;

	private String catId;

	private Date date;

	private transient String[] linkArtistIds;

	private Set<Artist> artists = Collections.synchronizedSet(new HashSet<Artist>());

	private transient String[] linkTrackIds;

	private Set<Track> tracks = Collections.synchronizedSet(new HashSet<Track>());

	// for hibernate
	public Album () {
	}
	// for searching
	public Album (String title) {
		this();
		this.name = title;
	}
	// for normal creation
	public Album (String title, String ownerId) {
		this(title);
		this.ownerId = ownerId;
	}

	/**
	 * Getter for the name field
	 * @return name
	 */
	public String getTitle () {
		return name;
	}

	/**
	 * Setter for the name field
	 * @param title
	 */
	public void setTitle (String title) {
		this.name = title;
	}

	/**
	 * Getter for the label field
	 * @return label
	 */
	public String getLabel () {
		return label;
	}

	/**
	 * Setter for the label field
	 * @param label
	 */
	public void setLabel (String label) {
		this.label = label;
	}

	/**
	 * Getter for the catId field (catalogue number)
	 * @return catId
	 */
	public String getCatId () {
		return catId;
	}

	/**
	 * Setter for the catId field (catalogue number)
	 * @param catId
	 */
	public void setCatId (String catId) {
		this.catId = catId;
	}

	/**
	 * Getter for the year field
	 * @return date
	 */
	public Date getDate () {
		return date;
	}

	/**
	 * Setter for the year field
	 * @param date
	 */
	public void setDate (Date date) {
		this.date = date;
	}

	/**
	 * Used to communicate with multi-selectors in the web user interface, 
	 * read by controllers
	 * @return array of artist ids to link with
	 */
	public String[] getLinkArtistIds () {
		return linkArtistIds;
	}

	/**
	 * Used to communicate with multi-selectors in the web user interface, 
	 * written by views
	 */
	public void setLinkArtistIds (String[] linkArtistIds) {
		this.linkArtistIds = linkArtistIds;
	}

	/**
	 * Used to communicate with multi-selectors in the web user interface, 
	 * read by controllers
	 * @return array of track ids to link with
	 */
	public String[] getLinkTrackIds () {
		return linkTrackIds;
	}

	/**
	 * Used to communicate with multi-selectors in the web user interface, 
	 * written by views
	 */
	public void setLinkTrackIds (String[] linkTrackIds) {
		this.linkTrackIds = linkTrackIds;
	}

	public Set<Artist> getArtists () {
		return artists;
	}
	public void setArtists (Set<Artist> artists) {
		this.artists = artists;
	}
	
	// Defensive, convenience methods
	/**
	 * Creates a two-way link between this album and an artist object (a band or a band member)
	 * 
	 * @param artist
	 */
	public void addToArtists (Artist artist) {
		this.getArtists().add(artist);
		artist.getAlbums().add(this);
	}

	/**
	 * Destroys a two-way link between this album and an artist object (a band or a band member)
	 * 
	 * @param artist
	 */
	public void removeFromArtists (Artist artist) {
		this.getArtists().remove(artist);
		artist.getAlbums().remove(this);
	}

	public Set<Track> getTracks () {
		return tracks;
	}
	public void setTracks (Set<Track> tracks) {
		this.tracks = tracks;
	}

	/**
	 * Creates a two-way link between this album and a track object
	 * 
	 * @param track
	 */
	public void addToTrackListing (Track track) {
		this.getTracks().add(track);
		track.getAlbums().add(this);
	}

	/**
	 * Destroys a two-way link between this album and a track object
	 * 
	 * @param track
	 */
	public void removeFromTrackListing (Track track) {
		this.getTracks().remove(track);
		track.getAlbums().remove(this);
	}

	/**
	 * Primarily used by remote access clients
	 * 
	 * @return a string representation of the inherent items for this album
	 */
	@Override
	public String toString () {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Title: ");
		stringBuilder.append(name);
		if (label != null) {
			stringBuilder.append(", Label: ");
			stringBuilder.append(label);
		}
		if (catId != null) {
			stringBuilder.append(", Cat.:  ");
			stringBuilder.append(catId);
		}
		if (date != null) {
			stringBuilder.append(", Date:  ");
			stringBuilder.append(date.toString());
		}
		return stringBuilder.toString();
	}
	
	/**
	 * Primarily used by remote access clients
	 * 
	 * @return a string representation of the linked items for this album
	 */
	public String getDetails () {
		StringBuilder stringBuilder = new StringBuilder();
		if (!artists.isEmpty()) {
			stringBuilder.append(" - Artists - ");
			stringBuilder.append(Constants.lineSeparator);
			for (Artist artist : artists) {
				stringBuilder.append(artist.getName());
				stringBuilder.append(Constants.lineSeparator);
			}
		}
		if (!tracks.isEmpty()) {
			stringBuilder.append(" - Tracks - ");
			stringBuilder.append(Constants.lineSeparator);
			for (Track track : tracks) {
				stringBuilder.append(track.getTitle());
				stringBuilder.append(Constants.lineSeparator);
			}
		}
		return stringBuilder.toString();
	}
	
	public boolean isLinked () {
		return (! getTracks().isEmpty()) || (! getArtists().isEmpty());
	}
}
