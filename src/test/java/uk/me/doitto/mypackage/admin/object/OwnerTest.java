package uk.me.doitto.mypackage.admin.object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.EnumSet;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OwnerTest {

	Owner owner;
	String username = "testuser";
	String password = "dfsjlkajsdflkajsdl";
	String pass1 = "twertwertwert";
	String pass2 = "cvncvbncvvbnc";
	String notes = "test notes";
	String role = "test role";
	int accountFlags = 31;
	int moduleFlags = 3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		owner = new Owner(username);
		owner.setPassword(password);
		owner.setPass1(pass1);
		owner.setPass2(pass2);
		owner.setRole(role);
		owner.setNotes(notes);
		owner.setAccountFlags(accountFlags);
		owner.setModuleFlags(moduleFlags);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOwner() {
		owner = new Owner();
	}

	@Test
	public void testOwnerString() {
		owner = new Owner(username);
	}

	@Test
	public void testGetUsername() {
		assertTrue(owner.getUsername().equals(username));
	}

	@Test
	public void testSetUsername() {
		String username = "newusername";
		owner.setUsername(username);
		assertTrue(owner.getUsername().equals(username));
	}

	@Test
	public void testGetPassword() {
		assertTrue(owner.getPassword().equals(password));
	}

	@Test
	public void testSetPassword() {
		String password = "zvzxcvzmxncvxzm";
		owner.setPassword(password);
		assertTrue(owner.getPassword().equals(password));
	}

	@Test
	public void testGetRole() {
		assertTrue(owner.getRole().equals(role));
	}

	@Test
	public void testSetRole() {
		String role = "new role";
		owner.setRole(role);
		assertTrue(owner.getRole().equals(role));
	}

	@Test
	public void testGetAccountFlags() {
		assertEquals(accountFlags, owner.getAccountFlags());
	}

	@Test
	public void testSetAccountFlags() {
		int accountFlags = 15;
		owner.setAccountFlags(accountFlags);
		assertEquals(accountFlags, owner.getAccountFlags());
	}

	@Test
	public void testGetModuleFlags() {
		assertEquals(moduleFlags, owner.getModuleFlags());
	}

	@Test
	public void testSetModuleFlags() {
		int moduleFlags = 3;
		owner.setModuleFlags(moduleFlags);
		assertEquals(moduleFlags, owner.getModuleFlags());
	}

	@Test
	public void testGetPass1() {
		assertTrue(owner.getPass1().equals(pass1));
	}

	@Test
	public void testSetPass1() {
		String pass1 = "zvzxcvzmxncvxzm";
		owner.setPass1(pass1);
		assertTrue(owner.getPass1().equals(pass1));
	}

	@Test
	public void testGetPass2() {
		assertTrue(owner.getPass2().equals(pass2));
	}

	@Test
	public void testSetPass2() {
		String pass2 = "zvzxcvzmxncvxzm";
		owner.setPass2(pass2);
		assertTrue(owner.getPass2().equals(pass2));
	}

	@Test
	public void testGetNotes() {
		assertTrue(owner.getNotes().equals(notes));
	}

	@Test
	public void testSetNotes() {
		String notes = "edited notes";
		owner.setNotes(notes);
		assertTrue(owner.getNotes().equals(notes));
	}

	@Test
	public void testGetRoles() {
		assertEquals(EnumSet.allOf(SecurityRole.class), owner.getRoles());
	}

	@Test
	public void testToggleEnabledStatus() {
		owner.toggleEnabledStatus();
		assertFalse(owner.isEnabled());
		owner.toggleEnabledStatus();
		assertTrue(owner.isEnabled());
	}

	@Test
	public void testIsEnabled() {
		assertTrue(owner.isEnabled());
	}

	@Test
	public void testSetEnabled() {
		owner.setEnabled(false);
		assertFalse(owner.isEnabled());
		owner.setEnabled(true);
		assertTrue(owner.isEnabled());
	}

	@Test
	public void testIsAccountNonExpired() {
		assertTrue(owner.isAccountNonExpired());
	}

	@Test
	public void testSetAccountNonExpired() {
		owner.setAccountNonExpired(false);
		assertFalse(owner.isAccountNonExpired());
		owner.setAccountNonExpired(true);
		assertTrue(owner.isAccountNonExpired());
	}

	@Test
	public void testIsAccountNonLocked() {
		assertTrue(owner.isAccountNonLocked());
	}

	@Test
	public void testSetAccountNonLocked() {
		owner.setAccountNonLocked(false);
		assertFalse(owner.isAccountNonLocked());
		owner.setAccountNonLocked(true);
		assertTrue(owner.isAccountNonLocked());
	}

	@Test
	public void testIsCredentialsNonExpired() {
		assertTrue(owner.isCredentialsNonExpired());
	}

	@Test
	public void testSetCredentialsNonExpired() {
		owner.setCredentialsNonExpired(false);
		assertFalse(owner.isCredentialsNonExpired());
		owner.setCredentialsNonExpired(true);
		assertTrue(owner.isCredentialsNonExpired());
	}

//	@Test
//	public void testIsGroup() {
//		assertTrue(owner.isGroup());
//	}
//
//	@Test
//	public void testSetGroup() {
//		owner.setGroup(false);
//		assertFalse(owner.isGroup());
//		owner.setGroup(true);
//		assertTrue(owner.isGroup());
//	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetAuthorities() {
		assertEquals(new GrantedAuthority[] { new GrantedAuthorityImpl(role) }, owner.getAuthorities());
	}

//	@Test
//	public void testIsMm() {
//		assertTrue(owner.isMm());
//	}
//
//	@Test
//	public void testSetMm() {
//		owner.setMm(false);
//		assertFalse(owner.isMm());
//		owner.setMm(true);
//		assertTrue(owner.isMm());
//	}
//
//	@Test
//	public void testIsRss() {
//		assertTrue(owner.isRss());
//	}
//
//	@Test
//	public void testSetRss() {
//		owner.setRss(false);
//		assertFalse(owner.isRss());
//		owner.setRss(true);
//		assertTrue(owner.isRss());
//	}

}
