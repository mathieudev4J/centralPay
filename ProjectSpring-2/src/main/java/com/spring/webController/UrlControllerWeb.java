package com.spring.webController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.entity.Urls;
import com.spring.repositories.RepositoryUrls;
import com.spring.servicesImpl.ServicesUrls;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/api/url")
public class UrlControllerWeb {

	@Autowired
	private RepositoryUrls repositoryUrls;

	private final ServicesUrls servicesUrls;
	@PostMapping("/short")
	public ResponseEntity<Map<String,String>> shortUrl(@RequestBody Map<String,String> request ){
	
		String originalUrl= request.get("url");
		
		if (originalUrl ==null || originalUrl.isEmpty()) {
			return ResponseEntity.badRequest().body(Map.of("error","Url invalide"));
		}
		//String shortUrl= servicesUrls.urlShort(originalUrl);
		String shortId=UUID.randomUUID().toString().substring(0, 6);
		Urls url=new Urls(originalUrl, shortId);
		repositoryUrls.save(url);
		return ResponseEntity.ok(Map.of("shortUrl",shortId));
		
	}
	
	@GetMapping("/{shortUrl}")
	public ResponseEntity viewToOriginalUrl(@PathVariable String shortUrl) {
	
	Optional<String> originalUrl=servicesUrls.getOriginalUrl(shortUrl);
		if((originalUrl.isPresent())){
	    
			return  ResponseEntity.ok(originalUrl);
		}
		if(shortUrl ==null) {
			return ResponseEntity.badRequest().build();
		}
		return null;
			
		}
	/*@GetMapping("/allshortUrls/{originalUrl}")
	public ResponseEntity getAllShortUrlsFromOriginalUrls(@PathVariable String originalUrl) {
		List <Urls> listUrls=repositoryUrls.findAll();
	    for (int i=0;i< listUrls.size(); i++) {
	    	Urls urls= listUrls.get(i);
	    	if (urls.getShortUrl().equals(originalUrl)){
	    		
	    		return ResponseEntity.ok(urls.getUrls());
	    	}
	    	else return ResponseEntity.badRequest().build();
	    }
		return ResponseEntity.noContent().build();
		*/
	}
	
	
	

