package io.selendroid.extension;

import io.selendroid.server.common.Response;
import io.selendroid.server.common.http.HttpRequest;

import java.lang.reflect.Type;
import java.util.Map;

import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class SharedPreferencesExtensionHandler extends
		InstrumentedExtensionHandler {

	public static final String PREFERENCE_NAME = "name";
	public static final String PREFERENCE_KEY = "key";
	public static final String PREFERENCE_VALUE = "value";

	public SharedPreferencesExtensionHandler(String mappedUri) {
		super(mappedUri);
	}

	@Override
	public Response handle(HttpRequest request) throws JSONException {
		String preferenceName = getPreferenceNameFromRequest(request);
		SharedPreferences sharedPreferences = getSharedPreferences(preferenceName);
		return handleSharedPreferences(request, sharedPreferences);
	}

	protected abstract Response handleSharedPreferences(HttpRequest request,
			SharedPreferences sharedPreferences);

	private String getPreferenceNameFromRequest(HttpRequest request) {
		return getRequestData(request, PREFERENCE_NAME);
	}

	protected String getPreferenceKeyFromRequest(HttpRequest request) {
		return getRequestData(request, PREFERENCE_KEY);
	}

	private SharedPreferences getSharedPreferences(String preferenceName) {
		return getContext().getSharedPreferences(preferenceName,
				Context.MODE_PRIVATE);
	}

	protected String getRequestData(HttpRequest request, String key) {
		Gson gson = new Gson();
		Type stringStringMap = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> map = gson
				.fromJson(request.body(), stringStringMap);
		return map.get(key).toString();
	}

}