package com.raisonne.bd.util.mail.templateprocessor;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.raisonne.bd.util.PropertyLoader;

/**
 * Velocity template processing class this class is capable of parsing a given
 * velocity template and return the string representation of the template. This
 * is a helper utility class for the mailing API which will use this class to
 * parse and get the equivalent string content of the Velocity template
 * <p>
 * 
 * @author Umesh Awasthi
 * @since 03/11/2011
 * @version 1.0
 * 
 */
public class TemplateProcessingEngine {

	Logger log = Logger.getLogger(TemplateProcessingEngine.class);
	private VelocityContext velocityContext;
	Properties properties = new Properties();
	VelocityEngine velocityEngine = new VelocityEngine();

	private TemplateProcessingEngine(Map model) {
		log.info("Setting required velocity engine properties");
		properties = PropertyLoader.loadProperties("velocity");
		Velocity.setApplicationAttribute("javax.servlet.ServletContext",
				model.get("servletContext"));
		Velocity.init(properties);
		velocityContext = new VelocityContext(model);
		velocityEngine.setApplicationAttribute("javax.servlet.ServletContext",
				model.get("servletContext"));
		velocityEngine.init(properties);
	}

	/**
	 * Method responsible for parsing the given velocity template and returning
	 * the parsed content as String value.This method will take the name of the
	 * target velocity template and will parse the template. Velocity engine
	 * will look this template at the specified location as configured in the
	 * velocity.properties file located at the root of the class path.
	 * 
	 * @param mailTemplate
	 *            target velocity template
	 * @return String parsed velocity template's string representation.
	 */
	public String parseTemplate(String mailTemplate) {
		Template template = null;
		try {
			template = Velocity.getTemplate(mailTemplate);
			System.out.println("template name :: " + template.getName());

			log.info("Templated searched and found: " + template);
		} catch (ResourceNotFoundException rnfe) {
			log.error("cannot find template " + template);
		} catch (ParseErrorException pee) {
			log.error("Syntax error in template " + template + ":" + pee);

		}
		StringWriter writer = new StringWriter();
		if (template != null) {
			velocityEngine.mergeTemplate(mailTemplate, "UTF-8",
					velocityContext, writer);
		}

		return writer.toString();
	}

	public static TemplateProcessingEngine getInstance(Map model) {
		return new TemplateProcessingEngine(model);

	}

}
