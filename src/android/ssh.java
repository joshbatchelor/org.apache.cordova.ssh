/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package org.apache.cordova.ssh;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import java.util.Properties;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import android.os.Bundle;
import android.app.Activity;

/**
 * This class provides access to vibration on the device.
 */
public class ssh extends CordovaPlugin {

    /**
     * Constructor.
     */
    public ssh() { }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArray of arguments for the plugin.
     * @param callbackContext   The callback context used when calling back into JavaScript.
     * @return                  True when the action was valid, false otherwise.
     */
    public String execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("execute")) {
            this.executeCommand(
            	args.getString(0), 
            	args.getString(1),
            	args.getString(2),
            	args.getString(3)
            );
        }
        else {
            return false;
        }

        // Only alert and confirm are async.
        callbackContext.success();
        return true;
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------

	public String executeCommand(String username, String password, String hostname, int port) throws Exception {    
	   
		JSch jsch = new JSch();
		Session session = jsch.getSession(username, hostname, 22);
		session.setPassword(password);
		   
		// Avoid asking for key confirmation
		Properties prop = new Properties();
		prop.put("StrictHostKeyChecking", "no");
		session.setConfig(prop);

		session.connect();

		// SSH Channel
		ChannelExec channelssh = (ChannelExec) session.openChannel("exec");      
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		channelssh.setOutputStream(baos);
 
		// Execute command
		channelssh.setCommand("ls");
		channelssh.connect();        
		channelssh.disconnect();

		return baos.toString();
	}
}
