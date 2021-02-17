package com.example.springboot.springbootcrud.service;

import com.example.springboot.springbootcrud.exception.ItemNotFoundException;
import com.example.springboot.springbootcrud.model.Item;
import com.example.springboot.springbootcrud.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item save(@RequestBody Item item){
		return itemRepository.save(item);
	}

	public Item update(@PathVariable(value = "id") Integer id,
			@RequestBody Item itemDetails) throws ItemNotFoundException {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Employee not found for this id :: " + id));

		item.setName(itemDetails.getName());
		item.setDescription(itemDetails.getDescription());

		final Item updatedItem = itemRepository.save(item);
		return updatedItem;
	}


	public List<Item> findAll(){
		return itemRepository.findAll();

	}
	public Optional<Item> findById(@PathVariable(value = "id") Integer id){
		return itemRepository.findById(id);
	}

	public void deleteById(@PathVariable(value = "id") Integer id){
		itemRepository.deleteById(id);
	}

}
