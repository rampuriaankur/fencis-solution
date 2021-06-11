package com.ank.fencis.util;

/**
 * Terminator used to terminate the program.
 * */
public class Response {
	/**
	 * Terminate program, and view exit message details.
	 * 
	 * @param message
	 *            message to view to the user before terminating.
	 * @param status
	 *            integer representation of the exist status.
	 * */
	public static void getResponse(final String message, final int status) {
		System.out.println(message);
		System.exit(status);
	}

	public static void yes() {
		getResponse("yes", 0);
	}

	public static void no() {
		getResponse("no", 0);
	}
}
