package com.alera.nextdrop.dto;

public class AlbumDTO {
	
	  private String title;
	  private String artist;
	  private String imageUrl;
	  private String spotifyUrl;
	public AlbumDTO(String title, String artist, String imageUrl, String spotifyUrl) {
		super();
		this.title = title;
		this.artist = artist;
		this.imageUrl = imageUrl;
		this.spotifyUrl = spotifyUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSpotifyUrl() {
		return spotifyUrl;
	}
	public void setSpotifyUrl(String spotifyUrl) {
		this.spotifyUrl = spotifyUrl;
	}
	
	  
}
