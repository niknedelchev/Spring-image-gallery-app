package com.nn.gallery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.gallery.model.Image;
import com.nn.gallery.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository repo;
	
	
	public void save(Image image) {
		repo.save(image);
	}
	
	public Optional<Image> findById(int id) {
		return repo.findById(id);
	}
	
	public List<Image> findAll(){
		return repo.findAll();
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
}
