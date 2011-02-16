package uk.me.doitto.mypackage.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import uk.me.doitto.mypackage.admin.object.Owner;
import uk.me.doitto.mypackage.admin.object.ResourceMessage;
import uk.me.doitto.mypackage.dao.GenericDAOIf;
import uk.me.doitto.mypackage.dao.GenericHibernateDAO;
import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.object.Artist;
import uk.me.doitto.mypackage.mm.object.Track;
import uk.me.doitto.mypackage.object.Ownable;
import uk.me.doitto.mypackage.object.PersistentClass;
import uk.me.doitto.mypackage.rss.object.AggregatedFeed;
import uk.me.doitto.mypackage.rss.object.CachedRssFeed;

public class DAOIntegrationTests {

	SessionFactory sessionFactory;
	
	GenericDAOIf<Album> albumDAO;
	GenericDAOIf<Track> trackDAO;
	GenericDAOIf<Artist> artistDAO;
	
	GenericDAOIf<AggregatedFeed> aggregateDAO;
	GenericDAOIf<CachedRssFeed> feedDAO;
	
	GenericDAOIf<ResourceMessage> resourceDAO;
	GenericDAOIf<Owner> ownerDAO;
	
	GenericDAOIf<Ownable> ownableDAO;
	GenericDAOIf<PersistentClass> persistentDAO ;

	Album album;
	Track track1;
	Track track2;
	Track track3;
	Artist artist1;
	Artist artist2;
	Artist artist3;
	
	String albumId, albumName;
	String track1Id, track1Name;
	String track2Id, track2Name;
	String track3Id, track3Name;
	String artist1Id, artist1Name;
	String artist2Id, artist2Name;
	String artist3Id, artist3Name;
	
	ResourceMessage resource;
	Owner owner;
	
	String resourceId, resourceName;
	String ownerId, userName;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sessionFactory = (SessionFactory)new FileSystemXmlApplicationContext("WEB-INF/config/spring/applicationContext.xml").getBean("hibernateSessionFactory");
		
		persistentDAO = new GenericHibernateDAO<PersistentClass>(PersistentClass.class, sessionFactory);
		ownableDAO = new GenericHibernateDAO<Ownable>(Ownable.class, sessionFactory);
		
		aggregateDAO = new GenericHibernateDAO<AggregatedFeed>(AggregatedFeed.class, sessionFactory);
		feedDAO = new GenericHibernateDAO<CachedRssFeed>(CachedRssFeed.class, sessionFactory);
		
		albumDAO = new GenericHibernateDAO<Album>(Album.class, sessionFactory);
		album = new Album();
		albumName = "test title";
		album.setTitle(albumName);
		album.setLabel("test label");
		album.setCatId("CAT123");
		album.setDate(new Date());
		
		trackDAO = new GenericHibernateDAO<Track>(Track.class, sessionFactory);
		track1 = new Track();
		track1Name = "track title 1";
		track1.setTitle(track1Name);
		track1.setDate(new Date());
		track1.setUrl("http://some.host/some/path/somefile1.ext");
		track2 = new Track();
		track2Name = "track title 2";
		track2.setTitle(track2Name);
		track2.setDate(new Date());
		track2.setUrl("http://some.host/some/path/somefile2.ext");
		track3 = new Track();
		track3Name = "track title 3";
		track3.setTitle(track3Name);
		track3.setDate(new Date());
		track3.setUrl("http://some.host/some/path/somefile3.ext");
		
		artistDAO = new GenericHibernateDAO<Artist>(Artist.class, sessionFactory);
		artist1 = new Artist();
		artist1Name = "band name 1";
		artist1.setName(artist1Name);
		artist2 = new Artist();
		artist2Name = "member name 1";
		artist2.setName(artist2Name);
		artist3 = new Artist();
		artist3Name = "member name 2";
		artist3.setName(artist3Name);
		
		resourceDAO = new GenericHibernateDAO<ResourceMessage>(ResourceMessage.class, sessionFactory);
		resource = new ResourceMessage();
		resourceName = "test.resource";
		resource.setName(resourceName);
		resource.setCountryCode("_en");
		resource.setValue("1234");
		
		ownerDAO = new GenericHibernateDAO<Owner>(Owner.class, sessionFactory);
		owner = new Owner();
		userName = "testUser";
		owner.setName(userName);
		owner.setRole("ROLE_NONE");
		owner.setNotes("test notes");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersist() {
		albumDAO.persist(album);
		trackDAO.persist(track1);
		trackDAO.persist(track2);
		trackDAO.persist(track3);
		artistDAO.persist(artist1);
		artistDAO.persist(artist2);
		artistDAO.persist(artist3);
		resourceDAO.persist(resource);
		ownerDAO.persist(owner);
	}

	@Test
	public void testFindAll() {
		assertTrue(! albumDAO.findAll().isEmpty());
		assertTrue(! trackDAO.findAll().isEmpty());
		assertTrue(! artistDAO.findAll().isEmpty());
		assertTrue(! resourceDAO.findAll().isEmpty());
		assertTrue(! ownerDAO.findAll().isEmpty());
	}

	@Test
	public void testFindByExample() {
		assertTrue(! albumDAO.findByExample(new Album(albumName)).isEmpty());
		assertTrue(! trackDAO.findByExample(new Track(track1Name)).isEmpty());
		assertTrue(! trackDAO.findByExample(new Track(track2Name)).isEmpty());
		assertTrue(! trackDAO.findByExample(new Track(track3Name)).isEmpty());
		assertTrue(! artistDAO.findByExample(new Artist(artist1Name)).isEmpty());
		assertTrue(! artistDAO.findByExample(new Artist(artist2Name)).isEmpty());
		assertTrue(! artistDAO.findByExample(new Artist(artist3Name)).isEmpty());
		assertTrue(! resourceDAO.findByExample(new ResourceMessage(resourceName)).isEmpty());
		assertTrue(! ownerDAO.findByExample(new Owner(userName)).isEmpty());
	}

//	@Test
//	public void testFindByQueryString() {
//		assertTrue(! albumDAO.findByQueryString("from albums").isEmpty());
//		assertTrue(! trackDAO.findAll().isEmpty());
//		assertTrue(! artistDAO.findAll().isEmpty());
//		fail("Not yet implemented");
//	}

	@Test
	public void testFindById() {
		for (Album album : albumDAO.findAll()) {
			assertNotNull(albumDAO.findById(album.getId()));
		}
		for (ResourceMessage resource : resourceDAO.findAll()) {
			assertNotNull(resourceDAO.findById(resource.getId()));
		}
	}

	@Test
	public void testPolymorphicFindAll() {
		List<PersistentClass> persistentList = persistentDAO.findAll();
		List<Ownable> ownableList = ownableDAO.findAll();
		assertTrue(! ownableList.isEmpty());
		assertTrue(! persistentList.isEmpty());
		assertEquals(ownableList.size(), albumDAO.findAll().size() + trackDAO.findAll().size() + artistDAO.findAll().size() + aggregateDAO.findAll().size());
		assertEquals(persistentList.size(), ownableList.size() + resourceDAO.findAll().size() + ownerDAO.findAll().size() + feedDAO.findAll().size());
	}

	@Test
	public void testDelete() {
		for (Album album : albumDAO.findByExample(new Album(albumName))) {
			albumDAO.delete(album);
		}
		for (Track track : trackDAO.findByExample(new Track(track1Name))) {
			trackDAO.delete(track);
		}
		for (Track track : trackDAO.findByExample(new Track(track2Name))) {
			trackDAO.delete(track);
		}
		for (Track track : trackDAO.findByExample(new Track(track3Name))) {
			trackDAO.delete(track);
		}
		for (Artist artist : artistDAO.findByExample(new Artist(artist1Name))) {
			artistDAO.delete(artist);
		}
		for (Artist artist : artistDAO.findByExample(new Artist(artist2Name))) {
			artistDAO.delete(artist);
		}
		for (Artist artist : artistDAO.findByExample(new Artist(artist3Name))) {
			artistDAO.delete(artist);
		}
		for (ResourceMessage resource : resourceDAO.findByExample(new ResourceMessage(resourceName))) {
			resourceDAO.delete(resource);
		}
		for (Owner owner : ownerDAO.findByExample(new Owner(userName))) {
			ownerDAO.delete(owner);
		}
	}
}
