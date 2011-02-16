package uk.me.doitto.mypackage.admin.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.AbstractMessageSource;

import uk.me.doitto.mypackage.admin.object.ResourceMessage;
import uk.me.doitto.mypackage.dao.GenericDAOIf;
import uk.me.doitto.mypackage.dao.GenericHibernateDAO;

public class ResourceService extends AbstractMessageSource implements ResourceServiceIf,ResourceWebServiceIf {
	
	private GenericDAOIf<ResourceMessage> messageDao;
	public void setMessageDao (GenericDAOIf<ResourceMessage> messageDao) {
		this.messageDao = messageDao;
	}
	
	public void createResource (ResourceMessage resourceMessage) {
		messageDao.persist(resourceMessage);
	}

	public void saveResource (ResourceMessage resourceMessage) {
		messageDao.rePersist(resourceMessage);
	}

	public void deleteResource (String resourceId) {
		messageDao.delete(messageDao.findById(resourceId));
	}
	
	public List<ResourceMessage> getAll () {
		return messageDao.findAll();
	}
	
	public ResourceMessage getResource (String resourceId) {
		return messageDao.findById(resourceId);
	}
	
	public ResourceMessage getResource (String key, String countryCode) {
		if (messageDao instanceof GenericHibernateDAO<?>) {
			return messageDao.findByExample(new ResourceMessage(key, countryCode)).get(0);
		} else {
			return null;
		}
	}
	
	public void setResource (String key, String countryCode, String value) {
		getResource(key, countryCode).setValue(value);
	}
	
	@Override
	protected MessageFormat resolveCode (String key, Locale locale) {
		return new MessageFormat(getResource(key, locale.getCountry()).getValue());
	}
	
	public String getResourceString (String key, String countryCode) {
		return getResource(key, countryCode).getValue();
	}
	
	public void setResourceString (String key, String countryCode, String value) {
		getResource(key, countryCode).setValue(value);
	}
}
