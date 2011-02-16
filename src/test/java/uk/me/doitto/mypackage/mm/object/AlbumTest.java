package uk.me.doitto.mypackage.mm.object;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlbumTest {
	
	Album album;
	String id = "ASDFGHJK";
	String ownerId = "ABCDEFGH";
	String title = "test title";
	String label = "test label";
	String catId = "test catId";
	Date date = new Date();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		album = new Album(title, ownerId);
		album.setLabel(label);
		album.setCatId(catId);
		album.setDate(date);
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testHashCode() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEqualsObject() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testAlbum() {
		album = new Album();
	}

	@Test
	public void testAlbumString() {
		album = new Album(title);
	}

	@Test
	public void testAlbumStringString() {
		album = new Album(title, ownerId);
	}

	@Test
	public void testGetTitle() {
		assertTrue(album.getTitle().equals(title));
	}

	@Test
	public void testSetTitle() {
		String title = "new title";
		album.setTitle(title);
		assertTrue(album.getTitle().equals(title));
	}

	@Test
	public void testGetLabel() {
		assertTrue(album.getLabel().equals(label));
	}

	@Test
	public void testSetLabel() {
		String label = "new label";
		album.setLabel(label);
		assertTrue(album.getLabel().equals(label));
	}

	@Test
	public void testGetCatId() {
		assertTrue(album.getCatId().equals(catId));
	}

	@Test
	public void testSetCatId() {
		String catId = "new catId";
		album.setCatId(catId);
		assertTrue(album.getCatId().equals(catId));
	}

	@Test
	public void testGetDate() {
		assertTrue(album.getDate().equals(date));
	}

	@Test
	public void testSetDate() {
		Date date = new Date();
		album.setDate(date);
		assertTrue(album.getDate().equals(date));
	}

//	@Test
//	public void testGetLinkArtistIds() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetLinkArtistIds() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetLinkTrackIds() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetLinkTrackIds() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetArtistList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetArtistList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddToArtistList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveFromArtistList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetTrackListing() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetTrackListing() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddToTrackListing() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveFromTrackListing() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCompareTo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDetails() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsLinked() {
//		fail("Not yet implemented");
//	}

}
