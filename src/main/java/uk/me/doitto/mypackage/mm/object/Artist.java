package uk.me.doitto.mypackage.mm.object;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uk.me.doitto.mypackage.globals.Constants;
import uk.me.doitto.mypackage.object.Ownable;
import uk.me.doitto.mypackage.object.PersistentClass;

/**
 * Class representing an entire band, a band member, a solo artist, a composer, or a performer
 * 
 * @author Ian Smith
 *
 */
public class Artist extends Ownable implements Serializable, Comparable<PersistentClass> {
	
	private static final long serialVersionUID = -613658538579595697L;

	private transient String[] linkMemberIds;

	private Set<Artist> members = Collections.synchronizedSet(new HashSet<Artist>());

	private transient String[] linkBandIds;

	private Set<Artist> bands = Collections.synchronizedSet(new HashSet<Artist>());

	private transient String[] linkAlbumIds;

	private Set<Album> albums = Collections.synchronizedSet(new HashSet<Album>());

	private transient String[] linkPerformanceIds;

	private Set<Track> performances = Collections.synchronizedSet(new HashSet<Track>());

//	private transient String[] linkWritingCreditIds;
//
//	private Set<Track> writingCredits = Collections.synchronizedSet(new HashSet<Track>());

	// for hibernate
	public Artist() {
	}
	// for searching
	public Artist(String name) {
		this();
		this.name = name;
	}
	// for normal creation
	public Artist (String name, String ownerId) {
		this(name);
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getLinkMemberIds() {
		return linkMemberIds;
	}

	public void setLinkMemberIds(String[] linkMemberIds) {
		this.linkMemberIds = linkMemberIds;
	}

	public String[] getLinkBandIds() {
		return linkBandIds;
	}

	public void setLinkBandIds(String[] linkBandIds) {
		this.linkBandIds = linkBandIds;
	}

	public String[] getLinkAlbumIds() {
		return linkAlbumIds;
	}

	public void setLinkAlbumIds(String[] linkAlbumIds) {
		this.linkAlbumIds = linkAlbumIds;
	}

	public String[] getLinkPerformanceIds() {
		return linkPerformanceIds;
	}

	public void setLinkPerformanceIds(String[] linkPerformanceIds) {
		this.linkPerformanceIds = linkPerformanceIds;
	}

//	public String[] getLinkWritingCreditIds() {
//		return linkWritingCreditIds;
//	}
//
//	public void setLinkWritingCreditIds(String[] linkWritingCreditIds) {
//		this.linkWritingCreditIds = linkWritingCreditIds;
//	}

	public Set<Artist> getMembers () {
		return members;
	}
	public void setMembers (Set<Artist> members) {
		this.members = members;
	}

	public Set<Artist> getBands () {
		return bands;
	}
	public void setBands (Set<Artist> bands) {
		this.bands = bands;
	}

	public Set<Album> getAlbums () {
		return albums;
	}
	public void setAlbums (Set<Album> albums) {
		this.albums = albums;
	}

	public Set<Track> getPerformances () {
		return performances;
	}
	public void setPerformances (Set<Track> performances) {
		this.performances = performances;
	}

//	public Set<Track> getWritingCredits () {
//		return writingCredits;
//	}
//	public void setWritingCredits (Set<Track> writingCredits) {
//		this.writingCredits = writingCredits;
//	}

	// Associations, convenience methods
	public void addToMembers(Artist member) {
		this.getMembers().add(member);
		member.getBands().add(this);
	}

	public void removeFromMembers(Artist member) {
		this.getMembers().remove(member);
		member.getBands().remove(this);
	}
	
	public void addToBands(Artist band) {
		this.getBands().add(band);
		band.getMembers().add(this);
	}

	public void removeFromBands(Artist band) {
		this.getBands().remove(band);
		band.getMembers().remove(this);
	}

	public String toString () {
		return "Name:  " + name;
	}
	
	public String getDetails () {
		StringBuilder stringBuilder = new StringBuilder();
		if (!members.isEmpty()) {
			stringBuilder.append(" - Members - ");
			stringBuilder.append(Constants.lineSeparator);
			for (Artist member : members) {
				stringBuilder.append(member.getName());
				stringBuilder.append(Constants.lineSeparator);
			}
		}
		if (!bands.isEmpty()) {
			stringBuilder.append(" - Bands - ");
			stringBuilder.append(Constants.lineSeparator);
			for (Artist band : bands) {
				stringBuilder.append(band.getName());
				stringBuilder.append(Constants.lineSeparator);
			}
		}
		if (!albums.isEmpty()) {
			stringBuilder.append(" - Albums - ");
			stringBuilder.append(Constants.lineSeparator);
			for (Album album : albums) {
				stringBuilder.append(album.getTitle());
				stringBuilder.append(Constants.lineSeparator);
			}
		}
		if (!performances.isEmpty()) {
			stringBuilder.append(" - Performances - ");
			stringBuilder.append(Constants.lineSeparator);
			for (Track performance : performances) {
				stringBuilder.append(performance.getTitle());
				stringBuilder.append(Constants.lineSeparator);
			}
		}
//		if (!writingCredits.isEmpty()) {
//			stringBuilder.append(" - Writing Credits - ");
//			stringBuilder.append(Constants.lineSeparator);
//			for (Track writingCredit : writingCredits) {
//				stringBuilder.append(writingCredit.getTitle());
//				stringBuilder.append(Constants.lineSeparator);
//			}
//		}
		return stringBuilder.toString();
	}
	
	public boolean isLinked () {
		return (! getAlbums().isEmpty()) || (! getMembers().isEmpty()) || (! getBands().isEmpty()) || (! getPerformances().isEmpty());
	}
}
