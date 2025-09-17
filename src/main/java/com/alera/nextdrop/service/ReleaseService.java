package com.alera.nextdrop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alera.nextdrop.client.SpotifyClient;
import com.alera.nextdrop.config.SpotifyProperties;
import com.alera.nextdrop.dto.AlbumDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReleaseService {

  private final AuthService authService;
  private final SpotifyClient client;
  private final SpotifyProperties props;
  private final ObjectMapper mapper = new ObjectMapper();

  public ReleaseService(AuthService authService, SpotifyClient client, SpotifyProperties props) {
    this.authService = authService;
    this.client = client;
    this.props = props;
  }

  public List<AlbumDTO> getNewReleases(Integer limit, String country, Integer offset) throws Exception {
    int lim = (limit == null || limit <= 0 || limit > 50) ? 20 : limit;
    String market = (country == null || country.isBlank()) ? props.getDefaultCountry() : country.toUpperCase();
    int off = (offset == null || offset < 0) ? 0 : offset;

    String url = props.getApiBaseUrl()
        + "/browse/new-releases?limit=" + lim
        + "&country=" + market
        + "&offset=" + off;

    String token = authService.getAccessToken();
    String json = client.get(url, token);

    JsonNode root = mapper.readTree(json);
    JsonNode items = root.path("albums").path("items");

    List<AlbumDTO> result = new ArrayList<>();
    for (JsonNode album : items) {
      String title = album.path("name").asText();
      String artist = album.path("artists").get(0).path("name").asText();
      String imageUrl = album.path("images").size() > 0 ? album.path("images").get(0).path("url").asText() : "";
      String spotifyUrl = album.path("external_urls").path("spotify").asText();
      result.add(new AlbumDTO(title, artist, imageUrl, spotifyUrl));
    }
    return result;
  }
}