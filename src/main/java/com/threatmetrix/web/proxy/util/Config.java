/**
 * 
 */
package com.threatmetrix.web.proxy.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sparmar
 *
 */
public class Config {
	private static final String ALLOW_ALL_PATTERN = ".*";
	private static final int DEFAULT_PORT = 8083;
	private static final String DEFAULT_URL_HANDLER = "proxy";
	String urlHandler;
	Integer port;
	String allowedMachines;
	String allowedPorts;

	public Config() {
		super();
		this.urlHandler = DEFAULT_URL_HANDLER;
		this.port = DEFAULT_PORT;
		this.allowedMachines = ALLOW_ALL_PATTERN;
		this.allowedPorts = ALLOW_ALL_PATTERN;
	}

	public Config(String[] arguments) {
		super();

		Map<String, String> kvs = Arrays.asList(arguments).stream().map(elem -> elem.split(":"))
				.collect(Collectors.toMap(e -> e[0], e -> e[1]));

		this.urlHandler = kvs.get("urlHandler") == null ? DEFAULT_URL_HANDLER : kvs.get("urlHandler");
		this.port = kvs.get("port") == null ? DEFAULT_PORT : Integer.valueOf(kvs.get("port"));
		this.allowedMachines = kvs.get("allowedMachines") == null ? ALLOW_ALL_PATTERN : kvs.get("allowedMachines");
		this.allowedPorts = kvs.get("allowedPorts") == null ? ALLOW_ALL_PATTERN : kvs.get("allowedPorts");
	}

	/**
	 * @param urlHandler
	 * @param port
	 * @param allowedMachines
	 * @param allowedPorts
	 */
	public Config(String urlHandler, Integer port, String allowedMachines, String allowedPorts) {
		super();
		this.urlHandler = urlHandler;
		this.port = port;
		this.allowedMachines = allowedMachines;
		this.allowedPorts = allowedPorts;
	}

	/**
	 * @return the urlHandler
	 */
	public String getUrlHandler() {
		return urlHandler;
	}

	/**
	 * @param urlHandler
	 *            the urlHandler to set
	 */
	public void setUrlHandler(String urlHandler) {
		this.urlHandler = urlHandler;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the allowedMachines
	 */
	public String getAllowedMachines() {
		return allowedMachines;
	}

	/**
	 * @param allowedMachines
	 *            the allowedMachines to set
	 */
	public void setAllowedMachines(String allowedMachines) {
		this.allowedMachines = allowedMachines;
	}

	/**
	 * @return the allowedPorts
	 */
	public String getAllowedPorts() {
		return allowedPorts;
	}

	/**
	 * @param allowedPorts
	 *            the allowedPorts to set
	 */
	public void setAllowedPorts(String allowedPorts) {
		this.allowedPorts = allowedPorts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Config [urlHandler=" + urlHandler + ", port=" + port + ", allowedMachines=" + allowedMachines
				+ ", allowedPorts=" + allowedPorts + "]";
	}
}
