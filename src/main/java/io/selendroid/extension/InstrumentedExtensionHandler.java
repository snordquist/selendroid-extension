package io.selendroid.extension;

import android.content.Context;
import io.selendroid.server.ServerInstrumentation;
import io.selendroid.server.common.BaseRequestHandler;
import io.selendroid.server.common.SelendroidResponse;
import io.selendroid.server.common.StatusCode;
import io.selendroid.server.common.http.HttpRequest;

public abstract class InstrumentedExtensionHandler extends BaseRequestHandler {

	public InstrumentedExtensionHandler(String mappedUri) {
		super(mappedUri);
	}

	protected Context getContext() {
		Context context = ServerInstrumentation.getInstance()
				.getTargetContext();
		return context;
	}

	protected SelendroidResponse success(HttpRequest request) {
		return new SelendroidResponse(getSessionId(request), StatusCode.SUCCESS);
	}

}