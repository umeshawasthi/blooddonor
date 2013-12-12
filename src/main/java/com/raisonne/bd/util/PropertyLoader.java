/**
 * 
 */

package com.raisonne.bd.util;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * A generic Abstract class for property loader
 * this class will scan the existing folder structure for the given property file 
 * and will read the properties in to Property object
 * 
 * @author Umesh Awasthi
 * @since 03/11/2011
 * @version 1.0
 *
 */
public abstract class PropertyLoader {

	
	    /**
	     * Looks up a resource named 'name' in the classpath.The name is assumed to be absolute
	     * and can use either "/" or "." 
	     * following names refer to the same resource:
	     * <pre>
	     * some.pkg.Resource
	     * some.pkg.Resource.properties
	     * some/pkg/Resource
	     * some/pkg/Resource.properties
	     * /some/pkg/Resource
	     * /some/pkg/Resource.properties
	     * </pre>
	     * <p>
	     * In case file is at the root of classpath only name of the file should be supplied
	     * without any extension as this will only work for properties file for files at other location
	     * need to supply the package structure along wiith the file name as an argument.
	     * 
	     * @param name classpath resource name [may not be null]
	     * @param loader classloader through which to load the resource [null
	     * is equivalent to the application loader]
	     * 
	     * @return resource converted to java.util.Properties [may be null if the
	     * resource was not found and THROW_ON_LOAD_FAILURE is false]
	     * @throws IllegalArgumentException if the resource was not found and
	     * THROW_ON_LOAD_FAILURE is true
	     */
	    public static Properties loadProperties (String name, ClassLoader loader)
	    {
	        if (name == null)
	            throw new IllegalArgumentException ("null input: name");
	        
	        if (name.startsWith ("/"))
	            name = name.substring (1);
	            
	        if (name.endsWith (SUFFIX))
	            name = name.substring (0, name.length () - SUFFIX.length ());
	        
	        Properties result = null;
	        
	        InputStream in = null;
	        try
	        {
	            if (loader == null) loader = ClassLoader.getSystemClassLoader ();
	            
	            if (LOAD_AS_RESOURCE_BUNDLE)
	            {    
	                name = name.replace ('/', '.');
	                // Throws MissingResourceException on lookup failures:
	                final ResourceBundle rb = ResourceBundle.getBundle (name,
	                    Locale.getDefault (), loader);
	                
	                result = new Properties ();
	                for (Enumeration<?> keys = rb.getKeys (); keys.hasMoreElements ();)
	                {
	                    final String key = (String) keys.nextElement ();
	                    final String value = rb.getString (key);
	                    
	                    result.put (key, value);
	                } 
	            }
	            else
	            {
	                name = name.replace ('.', '/');
	                
	                if (! name.endsWith (SUFFIX))
	                    name = name.concat (SUFFIX);
	                                
	                // Returns null on lookup failures:
	                in = loader.getResourceAsStream (name);
	                if (in != null)
	                {
	                    result = new Properties ();
	                    result.load (in); // Can throw IOException
	                }
	            }
	        }
	        catch (Exception e)
	        {
	            result = null;
	        }
	        finally
	        {
	            if (in != null) try { in.close (); } catch (Throwable ignore) {}
	        }
	        
	        if (THROW_ON_LOAD_FAILURE && (result == null))
	        {
	            throw new IllegalArgumentException ("could not load [" + name + "]"+
	                " as " + (LOAD_AS_RESOURCE_BUNDLE
	                ? "a resource bundle"
	                : "a classloader resource"));
	        }
	        
	        return result;
	    }
	    
	    /**
	     * A convenience overload of {@link #loadProperties(String, ClassLoader)}
	     * that uses the current thread's context classloader.
	     */
	    public static Properties loadProperties (final String name)
	    {
	        return loadProperties (name,
	            Thread.currentThread ().getContextClassLoader ());
	    }
	        
	    private static final boolean THROW_ON_LOAD_FAILURE = true;
	    private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;
	    private static final String SUFFIX = ".properties";
	} // End of class

