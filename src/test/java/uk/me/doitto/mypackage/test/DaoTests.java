package uk.me.doitto.mypackage.test;

import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import uk.me.doitto.mypackage.dao.GenericDAOIf;
import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.object.Artist;
import uk.me.doitto.mypackage.mm.object.Track;

@SuppressWarnings("unchecked")
public class DaoTests extends TestCase {
	
	ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/spring/applicationContext.xml");
	
	GenericDAOIf<Album> albumDAO = (GenericDAOIf<Album>)applicationContext.getBean("albumHibernateDao");
	GenericDAOIf<Track> trackDAO = (GenericDAOIf<Track>)applicationContext.getBean("trackHibernateDao");
	GenericDAOIf<Artist> artistDAO = (GenericDAOIf<Artist>)applicationContext.getBean("artistHibernateDao");
	
	Album album = new Album();
	Track track1 = new Track();
	Track track2 = new Track();
	Track track3 = new Track();
	Artist artist1 = new Artist();
	Artist artist2 = new Artist();
	Artist artist3 = new Artist();
	
	String albumId;

	public DaoTests (String test){
		super(test);
	}
	
	public void setUp () throws Exception {
		super.setUp();
	}
	
    public static Test suite () {
    	TestSuite testSuite = new TestSuite();
    	testSuite.addTest(new DaoTests("testSaveAlbum"));
    	testSuite.addTest(new DaoTests("testEditAlbum"));
//    	testSuite.addTest(new DaoTests("testSaveTracks"));
//    	testSuite.addTest(new DaoTests("testSaveArtists"));
//    	testSuite.addTest(new DaoTests("testPrintAll"));
//    	testSuite.addTest(new DaoTests("testFindTrackByExample"));
    	testSuite.addTest(new DaoTests("testDeleteMusic"));
   	return testSuite;
    }
	
    public static void main (String args[]) {
        junit.textui.TestRunner.run(suite());
    }
    
	public void testSaveAlbum () {
		album.setTitle("test title");
		album.setLabel("test label");
		album.setCatId("CAT123");
		album.setDate(new Date());
		try {
			albumDAO.rePersist(album);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}		
    
	public void testEditAlbum () {
		Album albumTemplate = new Album();
		albumTemplate.setTitle("test title");
		try {
//			albumId = (String)albumDAO.findByExample(albumTemplate).get(0).getId();
//			System.out.println(albumId);
//			Album editedAlbum = albumDAO.findById(albumId);
			Album editedAlbum = albumDAO.findByExample(albumTemplate).get(0);
			editedAlbum.setTitle("edited test title");
			editedAlbum.setLabel("edited test label");
			editedAlbum.setCatId("CAT321");
			albumDAO.rePersist(editedAlbum);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}		
    
	public void testSaveTracks () {
		track1.setTitle("track title 1");
		track1.setDate(new Date());
		track1.setUrl("http://some.host/some/path/somefile1.ext");
		track2.setTitle("track title 2");
		track2.setDate(new Date());
		track2.setUrl("http://some.host/some/path/somefile2.ext");
		track3.setTitle("track title 3");
		track3.setDate(new Date());
		track3.setUrl("http://some.host/some/path/somefile3.ext");
		try {
			trackDAO.rePersist(track1);
			trackDAO.rePersist(track2);
			trackDAO.rePersist(track3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}		
    
	public void testSaveArtists () {
		artist1.setName("band name 1");
		artist2.setName("member name 1");
		artist3.setName("member name 2");
		try {
			artistDAO.rePersist(artist1);
			artistDAO.rePersist(artist2);
			artistDAO.rePersist(artist3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}		
    
	public void testDeleteMusic () {
		try {
			albumDAO.delete(album);
			trackDAO.delete(track1);
			trackDAO.delete(track2);
			trackDAO.delete(track3);
			artistDAO.delete(artist1);
			artistDAO.delete(artist2);
			artistDAO.delete(artist3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}		
    
	public void testFindTrackByExample () {
		Track track;
		track = new Track();
		track.setTitle("track title 1");
		try {
			System.out.println(trackDAO.findByExample(track).get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
		track = new Track();
		track.setUrl("http://some.host/some/path/somefile2.ext");
		try {
			System.out.println(trackDAO.findByExample(track).get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}		
}
