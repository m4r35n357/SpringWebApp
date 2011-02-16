package uk.me.doitto.mypackage.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import uk.me.doitto.mypackage.admin.object.ResourceMessage;

public class EditResourceFormController extends AddResourceFormController {
	
	protected void persist (ResourceMessage resourceMessage) {
		resourceService.saveResource(resourceMessage);
	}

	protected Object formBackingObject (HttpServletRequest request) throws ServletRequestBindingException {
		return resourceService.getResource(ServletRequestUtils.getRequiredStringParameter(request, "id"));
	}
}
