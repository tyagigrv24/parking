package com.demo.parking.util;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
	/**
	 *
	 * @param <X>           type of the response
	 * @param maybeResponse response to return if present
	 * @return response containing {@code maybeResponse} if present or
	 *         {@link HttpStatus#NOT_FOUND}
	 */
	public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
		return wrapOrNotFound(maybeResponse, null);
	}

	/**
	 *
	 * @param <X>           type of the response
	 * @param maybeResponse response to return if present
	 * @param header        headers to be added to the response
	 * @return response containing {@code maybeResponse} if present or
	 *         {@link HttpStatus#NOT_FOUND}
	 */
	public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
		return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
