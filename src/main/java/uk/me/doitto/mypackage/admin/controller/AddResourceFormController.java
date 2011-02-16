package uk.me.doitto.mypackage.admin.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;

import uk.me.doitto.mypackage.admin.object.ResourceMessage;
import uk.me.doitto.mypackage.admin.service.ResourceServiceIf;

public class AddResourceFormController extends SimpleFormController {

	protected ResourceServiceIf resourceService;
	public void setResourceService (ResourceServiceIf resourceService) {
		this.resourceService = resourceService;
	}

	protected void persist (ResourceMessage resourceMessage) {
		resourceService.createResource(resourceMessage);
	}
	
	public void doSubmitAction (Object command) {
		ResourceMessage resourceMessage = (ResourceMessage)command;
		persist(resourceMessage);
	}
}
