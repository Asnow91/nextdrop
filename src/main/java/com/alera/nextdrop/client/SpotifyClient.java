package com.alera.nextdrop.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

@Component
public class SpotifyClient {

  private final HttpClient http = HttpClient.newHttpClient();

  public String get(String url, String bearerToken) throws Exception {
    HttpRequest req = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Authorization", "Bearer " + bearerToken)
        .GET()
        .build();

    HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());

    int status = res.statusCode();
    if (status >= 200 && status < 300) {
      return res.body();
    }
    throw new RuntimeException("Spotify GET failed: " + status);
  }
}