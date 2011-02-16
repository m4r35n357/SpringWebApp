package uk.me.doitto.mypackage.mm.object;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public final class ArtistValidator implements Validator {
	
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return Artist.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "required");
	}
}
