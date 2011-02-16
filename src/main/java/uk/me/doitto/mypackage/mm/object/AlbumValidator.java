package uk.me.doitto.mypackage.mm.object;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public final class AlbumValidator implements Validator {
	
	@SuppressWarnings("unchecked")
	public boolean supports (Class clazz) {
		return Album.class.isAssignableFrom(clazz);
	}

	public void validate (Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Required Field!", "Required Field!");
	}
}
