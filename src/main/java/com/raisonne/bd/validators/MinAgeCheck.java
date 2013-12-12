package com.raisonne.bd.validators;

import static net.sf.oval.Validator.getCollectionFactory;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.constraint.DateRange;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

public class MinAgeCheck extends AbstractAnnotationCheck<MinAge> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer minAge;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configure(final MinAge constraintAnnotation)
	{
		super.configure(constraintAnnotation);
		setMinAge(constraintAnnotation.minAge());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> createMessageVariables()
	{
		final Map<String, String> messageVariables = getCollectionFactory().createMap(1);
		messageVariables.put("minAge", minAge == null ? ".." : minAge.toString());
		return messageVariables;
	}

	
	@Override
	public boolean isSatisfied(Object validatedObject, Object valueToValidate,
			OValContext context, Validator validator) throws OValException {
		if (valueToValidate == null) return true;
		long valueInMillis = -1;
		// check if the value is a Date
				if (valueToValidate instanceof Date)
				{
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR,-getMinAge());
					valueInMillis = cal.getTimeInMillis();
					Date currentDate = new Date();
					currentDate.getTime();
					if(valueInMillis < ((Date)valueToValidate).getTime())
						return false;
				} 
					return true;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	
}
