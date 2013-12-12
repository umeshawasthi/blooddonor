package com.raisonne.bd.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * This utility class is created to store maps of states and districts so that
 * it could be available in whole application
 * 
 * @author Arun
 * 
 */
public class StateInfoUtils {

	public static SortedMap<String, String> STATES = new TreeMap<String, String>();
	public static Map<String, List<String>> DISTRICTS = new HashMap<String, List<String>>();

	public static Map<String, String> getStates() {
		return Collections.unmodifiableMap(STATES);
	}

	public static Map<String, List<String>> getDistricts() {
		return Collections.unmodifiableMap(DISTRICTS);
	}

	public static void addState(String state, String stateName) {
		STATES.put(state, stateName);
	}

	public static void addDistricts(String state, List<String> districts) {
		DISTRICTS.put(state, districts);
	}

}
