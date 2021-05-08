package com.ada.pay.common;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import feign.Headers;
import feign.RequestLine;

@FeignClient(name = "dyClient")
public interface DyClient {
	@Headers({"Content-Type: application/json","Accept: application/json"})
	@RequestLine("POST")
	public @ResponseBody String commonRequest(URI baseUri,@RequestBody Object req);
	
	@Headers({"Content-Type: application/json","Accept: application/json"})
	@RequestLine("GET")
	public @ResponseBody String commonGetRequest(URI baseUri);
}
