package uk.me.doitto.mypackage.admin.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;

import uk.me.doitto.mypackage.admin.object.Owner;
import uk.me.doitto.mypackage.admin.service.OwnerServiceIf;

public class AddAccountFormController extends SimpleFormController {

	protected OwnerServiceIf ownerService;
	public void setOwnerService (OwnerServiceIf ownerService) {
		this.ownerService = ownerService;
	}

	protected void persist (Owner owner) {
		ownerService.createOwner(owner);
	}
	
	public void doSubmitAction (Object command) {
		Owner owner = (Owner)command;
		String newPassword = owner.getPass1();
		if ((! newPassword.isEmpty()) && newPassword.equals(owner.getPass2())) {
			owner.setPassword(ownerService.encodePassword(newPassword, owner.getUsername()));
		}
		persist(owner);
	}
}
