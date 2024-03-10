package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;

//@CrossOrigin("*")
@Controller
public class SongController {

	@Autowired
	SongServiceimpl ssl;

	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song) {

		String link = song.getLink();

		boolean status = ssl.linkExists(link);

		if(status == false) {
			ssl.addSong(song);
			System.out.println("song added");
		}else {
			System.out.println("song is already exist");
		}
		return "addsong";
	}

	@GetMapping("/viewsong")
	public String viweSongs(Model model) {
		List<Song> songList = ssl.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "displaysongs";
	}
//	@GetMapping("/viewsongs")
//	public @ResponseBody List<Song> viewSongs(){
//		return ssl.fetchAllSongs();
	
//	}
	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		boolean premium = true;
		if(premium) {
			List<Song> songList = ssl.fetchAllSongs();
			model.addAttribute("songs",songList);
			return "displaysongs";
		}else {
			return "subscription";
		}
	}
	
}
