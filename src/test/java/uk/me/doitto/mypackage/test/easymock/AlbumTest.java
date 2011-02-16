package uk.me.doitto.mypackage.test.easymock;

import java.util.Date;

import junit.framework.TestCase;
import uk.me.doitto.mypackage.mm.object.Album;

public class AlbumTest extends TestCase {

	private Album album = new Album();
	
	private static final String[] idStringArray = {"testId1", "testId2", "testId3"};
	
	public void setUp() {
//		Track track = (Track)MockControl.createControl(Track.class).getMock();
//		Artist artist = (Artist)MockControl.createControl(Artist.class).getMock();
//		Track track = new Track();
//		Artist artist = new Artist();
	}
	
	public void testSetId () {
		album.setId("testId");
	}
	
	public void testGetId () {
		album.getId();
	}
	
	public void testSetTitle () {
		album.setTitle("test album a");
	}
	
	public void testGetTitle () {
		album.getTitle();
	}
	
	public void testSetLabel () {
		album.setTitle("test label a");
	}
	
	public void testGetLabel () {
		album.getLabel();
	}
	
	public void testSetCatId () {
		album.setCatId("catid a");
	}
	
	public void testGetCatId () {
		album.getCatId();
	}
	
	public void testSetDate () {
		album.setDate(new Date());
	}
	
	public void testGetDate () {
		album.getDate();
	}
	
	public void testSetLinkArtistIds () {
		album.setLinkArtistIds(idStringArray);
	}
	
	public void testGetLinkArtistIds () {
		album.getLinkArtistIds();
	}
	
	public void testSetLinkTrackIds () {
		album.setLinkTrackIds(idStringArray);
	}
	
	public void testGetLinkTrackIds () {
		album.getLinkTrackIds();
	}
}
