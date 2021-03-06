/*-
 * ============LICENSE_START=======================================================
 * VID
 * ================================================================================
 * Copyright (C) 2017 - 2019 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.onap.vid.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class VidServiceUnavailableException.
 */
@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE)
public class VidServiceUnavailableException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new vid service unavailable exception.
	 */
	public VidServiceUnavailableException() {
		super();
	}
	
	/**
	 * Instantiates a new vid service unavailable exception.
	 *
	 * @param msg the msg
	 */
	public VidServiceUnavailableException(String msg) {
		super(msg);
	}
	
	/**
	 * Instantiates a new vid service unavailable exception.
	 *
	 * @param t the t
	 */
	public VidServiceUnavailableException(Throwable t) {
		super(t);
	}
	
	/**
	 * Instantiates a new vid service unavailable exception.
	 *
	 * @param msg the msg
	 * @param t the t
	 */
	public VidServiceUnavailableException(String msg, Throwable t) {
		super(msg, t);
	}
}
