package com.nn.gallery.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nn.gallery.model.Image;
import com.nn.gallery.service.ImageService;

@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping(path = "/")
	public String showIndexPage() {
		return "index";
	}

	@PostMapping(path = "/")
	public String handleImageUpload(@RequestParam(name = "description") String description,
			@RequestParam(name = "file") MultipartFile file) {
		
		String filename = file.getOriginalFilename();
		try {
			Image image = new Image();
			image.setDescription(description);
			image.setData(file.getBytes());
			image.setFilename(filename);
			imageService.save(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "index";
	}

	@GetMapping(path = "/images")
	public String showAllImages(Model model) {
		model.addAttribute("images", imageService.findAll());
		
		return "allimages";
	}
	
	@GetMapping(path = "/image/{id}")
	public void showAllImages(@PathVariable(name = "id") int id, HttpServletResponse response) {
	
		Image image = imageService.findById(id).get();
		try {
			response.getOutputStream().write(image.getData());
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping(path = "/delete/{id}")
	public String deleteImage(@PathVariable(name = "id") int id) {
	
		imageService.deleteById(id);
		
		return "redirect:/images";
	}

}
