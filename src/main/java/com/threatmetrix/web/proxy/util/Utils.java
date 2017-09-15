package com.threatmetrix.web.proxy.util;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	private static final String SEP = "/";

	public static String buildDirectURL(URI requestURL, Config config) {
		String handler = config.getUrlHandler();
		String url = requestURL.toString();
		String[] tokens = url.split(SEP);
		String machine = tokens[2];
		String port = tokens[3];

		if (Utils.checkRegex(config.allowedMachines, machine) && Utils.checkRegex(config.allowedPorts, port)) {
			return "http://" + machine + ":" + port
					+ url.substring((SEP+ handler+SEP+machine+SEP+port).length());
		} else {
			return null;
		}
	}

	public static boolean checkRegex(String pattern, String str) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);

		if (m.find())
			return true;

		return false;
	}
}
