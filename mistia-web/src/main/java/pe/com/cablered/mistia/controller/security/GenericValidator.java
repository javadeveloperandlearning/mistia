package pe.com.cablered.mistia.controller.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("pe.com.eb.security.GenericValidator")
public class GenericValidator implements Validator {

	private static final String PATTERN = "^[a-zA-Z0-9ÁÉÍÓÚáéíóú@ .\\-_]*$";

	private static Pattern pattern;
	private static Matcher matcher;

	public GenericValidator() {
		pattern = Pattern.compile(PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());
		//System.out.println("matccherrrrr :" +value.toString());

		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("Formato no válido de Ingreso");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}
