package com.nn.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nn.gallery.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
