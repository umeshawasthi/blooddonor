package com.raisonne.bd.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.configuration.annotation.Constraint;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Constraint(checkWith = MinAgeCheck.class)
public @interface MinAge {

	/**
	 * error code passed to the ConstraintViolation object
	 */
	String errorCode() default "com.raisonne.bd.validators.minAge";


	/**
	 * message to be used for the ContraintsViolatedException
	 * 
	 * @see ConstraintViolation
	 */
	String message() default "com.raisonne.bd.constraint.minAge.violated";

	int minAge() default 18;

	/**
	 * The associated constraint profiles.
	 */
	String[] profiles() default {};

	/**
	 * severity passed to the ConstraintViolation object
	 */
	int severity() default 0;

}
