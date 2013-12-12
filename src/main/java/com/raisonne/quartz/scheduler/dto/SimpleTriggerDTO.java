/*
 * Copyright 2010-2011 Raisonne Techonologies.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 */
package com.raisonne.quartz.scheduler.dto;

import java.io.Serializable;

/**
 * <p>Data transfer Object responsible for carrying Simple 
 * trigger data from UI layer to the underlying API to create
 * the simple trigger and vice-versa.</p>
 * <p>
 * <p>All essential data needed to create trigger should be passed
 * using the DTO, a direct interaction can break the API.</p>
 * @author Umesh Awasthi
 *
 */
public class SimpleTriggerDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
