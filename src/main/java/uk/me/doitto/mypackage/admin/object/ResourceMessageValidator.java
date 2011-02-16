package uk.me.doitto.mypackage.admin.object;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public final class ResourceMessageValidator implements Validator {
	
	@SuppressWarnings("unchecked")
	public boolean supports (Class clazz) {
		return ResourceMessage.class.isAssignableFrom(clazz);
	}

	public void validate (Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key", "Required Field!", "Required Field!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "Required Field!", "Required Field!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "Required Field!", "Required Field!");
		ResourceMessage resourceMessage = (ResourceMessage)obj;
		if (! resourceMessage.getKey().matches(".*\\..*")) {
			errors.rejectValue("key", "Must contain a dot", "Must contain a dot");
		}
	}
}
