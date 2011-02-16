package uk.me.doitto.mypackage.admin.object;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public final class OwnerValidator implements Validator {
	
	private static final int length = 6;
	
	@SuppressWarnings("unchecked")
	public boolean supports (Class clazz) {
		return Owner.class.isAssignableFrom(clazz);
	}

	public void validate (Object obj, Errors errors) {
		// username field
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required Field!", "Required Field!");
		Owner owner = (Owner)obj;
		if (owner.getUsername().length() < length) {
			errors.rejectValue("username", "Must be at least " + length + " characters", "Must be at least " + length + " characters");
		}
		// password fields
		String newPassword = owner.getPass1();
		if ((! newPassword.isEmpty()) && (newPassword.length() < length)) {
			errors.rejectValue("pass1", "Must be at least " + length + " characters", "Must be at least " + length + " characters");
		}
		if (! newPassword.equals(owner.getPass2())) {
			errors.rejectValue("pass2", "Must be equal", "Must be equal!");
		}
	}
}
