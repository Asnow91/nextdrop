package com.alera.nextdrop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alera.nextdrop.dto.AlbumDTO;
import com.alera.nextdrop.service.ReleaseService;

import org.springframework.ui.Model;

@Controller
public class HomeController {

  private final ReleaseService releaseService;

  public HomeController(ReleaseService releaseService) {
    this.releaseService = releaseService;
  }

  @GetMapping("/")
  public String root(RedirectAttributes ra) {
    ra.addAttribute("limit", 20);
    ra.addAttribute("country", "IT");
    ra.addAttribute("offset", 0);
    return "redirect:/new-releases";
  }

  @GetMapping("/new-releases")
  public String newReleases(
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) String country,
      @RequestParam(required = false) Integer offset,
      Model model
  ) {
    int safeLimit = (limit != null && limit > 0 && limit <= 50) ? limit : 20;
    String safeCountry = (country != null && !country.isBlank()) ? country.toUpperCase() : "IT";
    int safeOffset = (offset != null && offset >= 0) ? offset : 0;

    try {
      List<AlbumDTO> albums = releaseService.getNewReleases(safeLimit, safeCountry, safeOffset);
      model.addAttribute("albums", albums);
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
    }

    model.addAttribute("limit", safeLimit);
    model.addAttribute("country", safeCountry);
    model.addAttribute("offset", safeOffset);

    return "index";
  }
}