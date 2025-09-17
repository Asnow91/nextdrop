package com.alera.nextdrop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "spotify")

public class SpotifyProperties {
	@NotBlank
	private String clientId;
	
	@NotBlank
	private String clientSecret;
	
	private String apiBaseUrl = "https://api.spotify.com/v1";
    private String authTokenUrl = "https://accounts.spotify.com/api/token";
    private String defaultCountry = "IT";
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}
	public void setApiBaseUrl(String apiBase) {
		this.apiBaseUrl = apiBase;
	}
	public String getAuthTokenUrl() {
		return authTokenUrl;
	}
	public void setAuthTokenUrl(String authTokenUrl) {
		this.authTokenUrl = authTokenUrl;
	}
	public String getDefaultCountry() {
		return defaultCountry;
	}
	public void setDefaultCountry(String defaultCountry) {
		this.defaultCountry = defaultCountry;
	}

    
}
