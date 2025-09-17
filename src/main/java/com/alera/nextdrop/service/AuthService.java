package com.alera.nextdrop.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.alera.nextdrop.config.SpotifyProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthService {

  private final SpotifyProperties props;
  private final HttpClient http = HttpClient.newHttpClient();

  public AuthService(SpotifyProperties props) {
    this.props = props;
  }

  public String getAccessToken() throws Exception {
    String pair = props.getClientId() + ":" + props.getClientSecret();
    String basic = Base64.getEncoder().encodeToString(pair.getBytes(StandardCharsets.UTF_8));

    HttpRequest req = HttpRequest.newBuilder()
        .uri(URI.create(props.getAuthTokenUrl()))
        .header("Authorization", "Basic " + basic)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
        .build();

    HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
    if (res.statusCode() < 200 || res.statusCode() >= 300) {
      throw new RuntimeException("token request failed: " + res.statusCode());
    }

    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(res.body());
    return node.get("access_token").asText();
  }
}