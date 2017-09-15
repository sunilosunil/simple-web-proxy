package com.threatmetrix.web.proxy.util;

import java.net.URI;

import junit.framework.TestCase;

public class UtilsTest extends TestCase {

	public final void testBuildDirectURL() {
		assertEquals("http://machine.domain.com:9999/url", 
				Utils.buildDirectURL(URI.create("/proxy/machine.domain.com/9999/url"), new Config()));
	}

	public final void testBuildDirectURLBadMachine() {
		String[] args =  {"urlHandler:myproxy","allowedMachines:nomachine.*","allowedPorts:9999"};
		Config conf = new Config(args);
		assertEquals(null, 
				Utils.buildDirectURL(URI.create("/proxy/machine.domain.com/9999/url"), conf));
	}
	
	public final void testBuildDirectURLCustomURLHandle() {
		String[] args =  {"urlHandler:myproxy"};
		Config conf = new Config(args);
		assertEquals("http://machine.domain.com:9999/url", 
				Utils.buildDirectURL(URI.create("/myproxy/machine.domain.com/9999/url"), conf));
	}
	
	
	public final void testBuildDirectURLBadPort() {
		String[] args =  {"allowedPorts:8089"};
		Config conf = new Config(args);
		assertEquals(null, 
				Utils.buildDirectURL(URI.create("/proxy/machine.domain.com/9999/url"), conf));
	}
	
	public final void testCheckRegexAll() {
		assertTrue(Utils.checkRegex(".*", "abc"));
	}
	
	public final void testCheckRegexPorts() {
		assertTrue(Utils.checkRegex("8083|8082|8043", "8082"));
	}
	
	public final void testCheckRegexMachines() {
		assertTrue(Utils.checkRegex("dwhdata.*", "dwhdata101.prod.sac.int.threatmetrix.com"));
	}
	
	public final void testCheckRegexMachinesFail() {
		assertFalse(Utils.checkRegex("dwhdata.*", "dwhmaster101.prod.sac.int.threatmetrix.com"));
	}
	
	public final void testCheckRegexPortsBlock() {
		assertFalse(Utils.checkRegex("8083|8082|8043", "8081"));
	}
}
