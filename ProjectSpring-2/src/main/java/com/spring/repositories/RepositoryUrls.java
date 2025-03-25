package com.spring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Urls;


public interface RepositoryUrls extends JpaRepository<Urls, Long>{
Optional<Urls> findByShortUrl(String shortUrl);

List<Urls> findByUrls(String urls);
}
