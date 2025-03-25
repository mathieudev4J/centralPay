package com.spring.webController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import lombok.Data;

@AllArgsConstructor
@Data
@Controller
@RequestMapping("/api/url")
public class UrlControllerWeb {

	@Autowired
	private RepositoryUrls repositoryUrls;

	private final ServicesUrls servicesUrls;
	@PostMapping("/short")
	public ResponseEntity<Map<String,String>> shortUrl(@RequestBody Map<String,String> request ){
	
		String originalUrl= request.get("url");
		
		if (originalUrl ==null || originalUrl.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		//String shortUrl= servicesUrls.urlShort(originalUrl);
		String shortId=UUID.randomUUID().toString().substring(0, 6);
		Urls url=new Urls(originalUrl, shortId);
		repositoryUrls.save(url);
		return ResponseEntity.ok(Map.of("shortUrl",shortId));
		
	}
	
	@GetMapping("/{shortUrl}")
	public ResponseEntity<?> viewToOriginalUrl(@PathVariable String shortUrl) {
	
	Optional<String> originalUrl=servicesUrls.getOriginalUrl(shortUrl);
		if((originalUrl.isPresent())){
	                int click=Urls.getClickUrl();
			return  ResponseEntity.ok(originalUrl+"Nombres de visites "+String.valueOf(click));
		}
		if(shortUrl ==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return null;
			
	
	}
	@QueryMapping
    public List<Urls> getAllUrls() {
        return repositoryUrls.findAll();
    }

    @QueryMapping
    public List<Urls> getUrlsByLongUrl(@Argument String urls) {
        return repositoryUrls.findByUrls(urls);
    }
}
	
	
	

