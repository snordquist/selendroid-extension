package io.selendroid.extension;

import com.google.gson.Gson;

public class JsonUtil {

	public static String toJson(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
	}

}
