/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

var exec = require('cordova/exec');


/**
 *
 * @param {Object} options
 * @param {Function} completeCallback
 * @param {Function} errorCallback
 */
module.exports = {
	connect: function(options, completeCallback, errorCallback) {
		exec(completeCallback, errorCallback, "ssh", "connect", options);
	}
}

var ssh_client = function() {
	var connection;
	
	return {
		
		
		
	}
}

function(message, completeCallback) {
	exec(completeCallback, null, "startApp", "startapp", [message]);
}