package io.selendroid.extension;

import static io.selendroid.extension.JsonUtil.toJson;

import java.util.HashMap;

import io.selendroid.server.common.Response;
import io.selendroid.server.common.SelendroidResponse;
import io.selendroid.server.common.StatusCode;
import io.selendroid.server.common.http.HttpRequest;
import android.content.SharedPreferences;

public class GetSharedPreferencesExtensionHandler extends SharedPreferencesExtensionHandler {

	public GetSharedPreferencesExtensionHandler(String mappedUri) {
		super(mappedUri);
	}

	protected Response handleSharedPreferences(HttpRequest request,
			SharedPreferences sharedPreferences) {
		String key = getPreferenceKeyFromRequest(request);
		Object value = sharedPreferences.getAll().get(key);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put(PREFERENCE_VALUE, value);
		return new SelendroidResponse(getSessionId(request), StatusCode.SUCCESS, toJson(result));
	}

}
