package com.raisonne.classfinder.util;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


/**
 * Utility to walk the Java classpath, and to find all classes which are assignable (i.e. inherit) 
 * a specified class. If no matching class is specified, will return all classes in the classpath
 * @author Umesh Awasthi
 *
 */
public class JavaClassFinder  {
	public static final String JAVA_CLASS_PATH_PROPERTY = "java.class.path";
	public static final String CUSTOM_CLASS_PATH_PROPERTY = "/DemoStruts21/WEB-INF/classes";

	//	private static Logger LOG = Logger.getLogger(JavaClassFinder.class);

	private ArrayList<Class<?>> foundClasses;
	private Class<?> toFind;
	private JavaClassFileWalker fileWalker;
	private ClassLoadingFileHandler fileHandler; 

	/**
	 * Finds all classes which are Assignable from the specified class
	 * @param toFind only classes which are subtypes or implementers of the this class are found
	 * @return List of class objects
	 */
	@SuppressWarnings("unchecked")
	public <T> List<Class<? extends T>> findAllMatchingTypes(Class<T> toFind,String path) {
		foundClasses = new ArrayList<Class<?>>();
		List<Class<? extends T>> returnedClasses = new ArrayList<Class<? extends T>>();
		this.toFind = toFind;
		walkClassPath(path);
		for (Class<?> clazz : foundClasses) {
			returnedClasses.add((Class<? extends T>) clazz);
		}
		return returnedClasses;
	}

	private void walkClassPath(String appPath) {
		fileHandler = new ClassLoadingFileHandler();
		fileWalker = new JavaClassFileWalker(fileHandler);

		String[] classPathRoots = appPath.split(File.pathSeparator);
				//getClassPathRoots();
		for (int i=0; i< classPathRoots.length; i++) {
			String path = classPathRoots[i];
			if (path.endsWith(".jar")) {
				//				LOG.warn("walkClassPath(): reading from jar not yet implemented, jar file=" + path);
				continue;
			}
			//			LOG.debug("walkClassPath(): checking classpath root: " + path);
			// have to reset class path base so it can instance classes properly
			fileHandler.updateClassPathBase(path);
			fileWalker.setBaseDir(path);
			fileWalker.walk();
		}			
	}

	public String[] getClassPathRoots() {
		String classPath;
		URLClassLoader urlClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		System.out.println(urlClassLoader.getURLs().toString());

		if (System.getProperties().containsKey(CUSTOM_CLASS_PATH_PROPERTY)) {
			//			LOG.debug("getClassPathRoots(): using custom classpath property to search for classes");
			classPath = System.getProperty(CUSTOM_CLASS_PATH_PROPERTY);
		} else {
			classPath = System.getProperty(JAVA_CLASS_PATH_PROPERTY);
		}
		//classPath="/WEB-INF/classes/";
		String[] pathElements = classPath.split(File.pathSeparator);
		//		LOG.debug("getClassPathRoots(): classPath roots=" + StringUtil.dumpArray(pathElements));
		return pathElements;
		//return "/DemoStruts21/WEB-INF/classes";
	}

	private void handleClass(Class<?> clazz) {
		boolean isMatch = false;
		isMatch = toFind == null || toFind.isAssignableFrom(clazz);
		if (isMatch) {
			foundClasses.add(clazz);
		}
	}


	/**
	 * FileFindHandler plugin for the JavaClassFileWalker object to 
	 * create a class object for matched class files
	 * @author Umesh Awasthi
	 *
	 */
	class ClassLoadingFileHandler extends FileFindHandlerAdapter {
		private FileToClassConverter converter;

		public void updateClassPathBase(String classPathRoot) {
			if (converter == null) {
				converter = new FileToClassConverter(classPathRoot);
			}
			converter.setClassPathRoot(classPathRoot);
		}
		@Override
		public void handleFile(File file) {
			// if we get a Java class file, try to convert it to a class
			Class<?> clazz = converter.convertToClass(file);
			if (clazz == null) {
				return;
			}	
			handleClass(clazz);
		}
	}


	/**
	 * Finds all classes in the classpath
	 * @return
	 */
	public List<Class<?>> findAllMatchingTypes() {
		return findAllMatchingTypes(null,null);
	}

	public int getScannedClassesCount() {
		if (fileWalker == null) {
			return 0;
		}
		return fileWalker.getAllFilesCount();
	}

}

