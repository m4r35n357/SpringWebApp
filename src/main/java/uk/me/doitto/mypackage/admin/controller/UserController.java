package uk.me.doitto.mypackage.admin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

import uk.me.doitto.mypackage.admin.object.Owner;
import uk.me.doitto.mypackage.admin.service.OwnerServiceIf;

public class UserController implements ThrowawayController {
	
	private OwnerServiceIf ownerService;
	public void setOwnerService (OwnerServiceIf ownerService) {
		this.ownerService = ownerService;
	}

	private String actionType;
	public void setActionType (String actionType) {
		this.actionType = actionType;
	}

	private String id;
	public void setId (String id) {
		this.id = id;
	}
	
	private ModelAndView buildOwnerView () {
		List<Owner> list = ownerService.getAllOwners();
		Collections.sort(list);
		return new ModelAndView("ownerAdminView", "ownerList", list);
	}
	
	public ModelAndView execute () throws Exception {
		if (actionType.equals("editOwners")) {
		} else  if (actionType.equals("removeOwner")) {
			ownerService.deleteOwner(id);
		} else  if (actionType.equals("toggleEnabled")) {
			ownerService.toggleEnabled(id);
		} else {
			return new ModelAndView("error");
		}
		return buildOwnerView();
	}
}
