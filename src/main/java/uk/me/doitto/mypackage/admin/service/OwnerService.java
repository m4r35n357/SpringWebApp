package uk.me.doitto.mypackage.admin.service;

import java.util.List;

import org.springframework.security.annotation.Secured;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import uk.me.doitto.mypackage.admin.object.Owner;
import uk.me.doitto.mypackage.dao.GenericDAOIf;
import uk.me.doitto.mypackage.dao.GenericHibernateDAO;

public class OwnerService implements OwnerServiceIf,UserDetailsService {

	private final Log log = LogFactory.getLog(getClass());
		
//	public static final String PASSWORD_SALT = "springApp";
	
	private GenericDAOIf<Owner> ownerDAO;
	public void setOwnerDAO (GenericDAOIf<Owner> ownerDAO) {
		this.ownerDAO = ownerDAO;
	}
	
	private PasswordEncoder passwordEncoder;
	public void setPasswordEncoder (PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	@Secured("ROLE_SUPERVISOR")
	public void createOwner (Owner owner) {
		log.debug("storeOwner called");
		ownerDAO.persist(owner);
	}

	@Override
	@Secured("ROLE_SUPERVISOR")
	public void saveOwner (Owner owner) {
		log.debug("storeOwner called");
		ownerDAO.rePersist(owner);
	}

	@Override
	@Secured("ROLE_SUPERVISOR")
	public void deleteOwner (String ownerId) {
		log.debug("deleteOwner called");
		Owner owner = getOwner(ownerId);
		if (! owner.isEnabled()) {
			ownerDAO.delete(getOwner(ownerId));
		}
	}
	
	@Override
	public Owner getOwner (String ownerId) {
		log.debug("getOwner called");
		return ownerDAO.findById(ownerId);
	}

	@Override
	public List<Owner> getAllOwners () {
		log.debug("getAllOwners called");
		return ownerDAO.findAll();
	}
	
	@Override
	@Secured("ROLE_SUPERVISOR")
	public void toggleEnabled (String ownerId) {
		log.debug("toggleEnabled called");
		getOwner(ownerId).toggleEnabledStatus();
	}
	
	@Override
	public String encodePassword (String password, Object salt) {
		log.debug(password);
		return passwordEncoder.encodePassword(password, salt);
	}
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException, DataAccessException {
		log.debug(username);
		try {			
			if (ownerDAO instanceof GenericHibernateDAO<?>) {
				return ownerDAO.findByExample(new Owner(username)).get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("User: " + username + " not found");
		}
	}
	
	@Override
	public String getCurrentOwnerId () {
		log.debug("");
		return ((Owner)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
	}
}
