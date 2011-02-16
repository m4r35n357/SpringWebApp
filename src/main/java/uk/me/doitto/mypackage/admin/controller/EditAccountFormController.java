package uk.me.doitto.mypackage.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import uk.me.doitto.mypackage.admin.object.Owner;

public class EditAccountFormController extends AddAccountFormController {

	protected void persist (Owner owner) {
		ownerService.saveOwner(owner);
	}

	protected Object formBackingObject (HttpServletRequest request) throws ServletRequestBindingException {
		return ownerService.getOwner(ServletRequestUtils.getRequiredStringParameter(request, "id"));
	}
}
