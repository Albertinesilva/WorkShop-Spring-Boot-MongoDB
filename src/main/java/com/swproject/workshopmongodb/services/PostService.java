package com.swproject.workshopmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swproject.workshopmongodb.domain.Post;
import com.swproject.workshopmongodb.repository.PostRepository;
import com.swproject.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepositoty;

	public Post findById(String id) {
		Optional<Post> obj = postRepositoty.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return postRepositoty.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepositoty.fullSearch(text, minDate, maxDate);
	}
}