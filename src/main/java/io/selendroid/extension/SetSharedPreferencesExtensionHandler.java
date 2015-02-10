/*
 * Copyright 2014 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.extension;


import io.selendroid.server.ServerInstrumentation;
import io.selendroid.server.common.BaseRequestHandler;
import io.selendroid.server.common.Response;
import io.selendroid.server.common.SelendroidResponse;
import io.selendroid.server.common.StatusCode;
import io.selendroid.server.common.http.HttpRequest;

import java.lang.reflect.Type;
import java.util.Map;

import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SetSharedPreferencesExtensionHandler extends BaseRequestHandler {

	public SetSharedPreferencesExtensionHandler(String mappedUri) {
		super(mappedUri);
	}

	@Override
	public Response handle(HttpRequest request) throws JSONException {
		String preferenceName = getRequestData(request, "name");
		String key = getRequestData(request, "key");
		String value = getRequestData(request, "value");
		editSharedPreference(preferenceName, key, value);
		return new SelendroidResponse(getSessionId(request), StatusCode.SUCCESS);
	}

	private void editSharedPreference(String preferenceName, String key,
			String value) {
		getSharedPreferences(preferenceName)//
				.edit()//
				.putString(key, value)//
				.apply();
	}

	private SharedPreferences getSharedPreferences(String preferenceName) {
		return getContext().getSharedPreferences(
				preferenceName, Context.MODE_PRIVATE);
	}

	private Context getContext() {
		Context context = ServerInstrumentation.getInstance()
				.getTargetContext();
		return context;
	}

	private String getRequestData(HttpRequest request, String key) {
		Gson gson = new Gson();
		Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
		Map<String,String> map = gson.fromJson(request.body(), stringStringMap);
		return map.get(key).toString();
	}

}
