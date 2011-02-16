package uk.me.doitto.mypackage.admin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

import uk.me.doitto.mypackage.admin.object.ResourceMessage;
import uk.me.doitto.mypackage.admin.service.ResourceServiceIf;

public class ResourceController implements ThrowawayController {
	
	private ResourceServiceIf resourceService;
	public void setResourceService (ResourceServiceIf resourceService) {
		this.resourceService = resourceService;
	}

	private String actionType;
	public void setActionType (String actionType) {
		this.actionType = actionType;
	}

	private String id;
	public void setId (String id) {
		this.id = id;
	}
	
	private ModelAndView buildResourceView () {
		List<ResourceMessage> list = resourceService.getAll();
		Collections.sort(list);
		return new ModelAndView("resourceAdminView", "resourceList", list);
	}
	
	public ModelAndView execute () throws Exception {
		if (actionType.equals("editResources")) {
		} else  if (actionType.equals("removeResource")) {
			resourceService.deleteResource(id);
		} else {
			return new ModelAndView("error");
		}
		return buildResourceView();
	}
}
