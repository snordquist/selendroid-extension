package io.selendroid.extension;

import io.selendroid.server.common.Response;
import io.selendroid.server.common.http.HttpRequest;
import android.content.SharedPreferences;

public class SetSharedPreferencesExtensionHandler extends SharedPreferencesExtensionHandler {

	public SetSharedPreferencesExtensionHandler(String mappedUri) {
		super(mappedUri);
	}

	protected Response handleSharedPreferences(HttpRequest request,
			SharedPreferences sharedPreferences) {
		String key = getPreferenceKeyFromRequest(request);
		String value = getPreferenceValueFromRequest(request);
		editSharedPreference(sharedPreferences, key, value);
		return success(request);
	}

	private String getPreferenceValueFromRequest(HttpRequest request) {
		return getRequestData(request, SharedPreferencesExtensionHandler.PREFERENCE_VALUE);
	}

	private void editSharedPreference(SharedPreferences sharedPreferences, String key,
			String value) {
		sharedPreferences
				.edit()//
				.putString(key, value)//
				.apply();
	}

}
