package com.example.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.dto.PostDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository userRepository;
	
	public List<Post> findAll(){
		return userRepository.findAll();
	}
	
	public Post findById(String id){
		Optional<Post> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Post insert(Post obj) {
		return userRepository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = findById(obj.getId());
		UpdateData(newObj, obj);
		return userRepository.save(newObj);
	}
 	
	private void UpdateData(Post newObj, Post obj) {
		newObj.setDate(obj.getDate());
		newObj.setTitle(obj.getTitle());
		newObj.setBody(obj.getBody());
	}

	public Post fromDTO(PostDTO obj) {
		return new Post(obj.getId(), obj.getDate(), obj.getTitle(), obj.getBody(), obj.getAuthor());
	}
	
	public List<Post> findByTitle(String text) {
		return userRepository.findByTitleContainingIgnoreCase(text);
	}
}
