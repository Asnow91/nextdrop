package com.alera.nextdrop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alera.nextdrop.dto.AlbumDTO;
import com.alera.nextdrop.service.ReleaseService;

@RestController
@RequestMapping("/api/releases")
public class ReleaseController {

  private final ReleaseService releaseService;

  public ReleaseController(ReleaseService releaseService) {
    this.releaseService = releaseService;
  }

  @GetMapping("/latest")
  public List<AlbumDTO> latest(
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) String country,
      @RequestParam(required = false) Integer offset
  ) {
    try {
      return releaseService.getNewReleases(limit, country, offset);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Spotify error", e);
    }
  }
}