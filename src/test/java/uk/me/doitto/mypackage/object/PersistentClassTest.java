/**
 * 
 */
package uk.me.doitto.mypackage.object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author ian
 *
 */
public class PersistentClassTest {
	
	private static class Subclass extends PersistentClass {
	}
	
	Subclass subclass = new Subclass();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		subclass.setId("startId");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.object.PersistentClass#hashCode()}.
	 */
	@Ignore("not ready yet")
	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.object.PersistentClass#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals(subclass.getId(), "startId"); 
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.object.PersistentClass#setId(java.lang.String)}.
	 */
	@Test
	public void testSetId() {
		subclass.setId("newId");
		assertEquals(subclass.getId(), "newId");
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.object.PersistentClass#equals(java.lang.Object)}.
	 */
	@Ignore("not ready yet")
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
