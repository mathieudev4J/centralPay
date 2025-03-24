package com.spring.servicesImpl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.entity.Urls;
import com.spring.repositories.RepositoryUrls;


@Service
public class ServicesUrls {

	@Autowired
	private RepositoryUrls repositoryUrls;
	
	public ServicesUrls(RepositoryUrls repositoryUrls) {
		super();
		this.repositoryUrls=repositoryUrls;
	}
    
	
	public String urlShort(String urlOrigine) {
	
		
		String  urlShort= Base64.getUrlEncoder()
				                .encodeToString(
				                urlOrigine.getBytes(StandardCharsets.UTF_8))
				                .substring(0,5);
		
		return urlShort;
	
}
	
	public Optional<String> getOriginalUrl(String shortUrl){
		return repositoryUrls.findByShortUrl(shortUrl)
				             .map(Urls::getUrls);
				           
	}
}
