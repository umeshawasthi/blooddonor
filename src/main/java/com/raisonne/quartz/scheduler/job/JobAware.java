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
package com.raisonne.quartz.scheduler.job;

import org.quartz.Job;

/**
 * <p>Extension point to use custom JobClass in place of generic
 * {@link GenericJob} being provided out of the box by Scheduler
 * Management API.</p>
 * <p>
 * Any Class which want to act as a JobClass have to implement this interface
 * to indicate underlying API.
 * </p>
 * @author Umesh Awasthi
 *
 */
public interface JobAware extends Job {

}
