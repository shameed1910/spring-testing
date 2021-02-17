package com.example.springboot.springbootcrud.controller;

import com.example.springboot.springbootcrud.model.Item;
import com.example.springboot.springbootcrud.repository.ItemRepository;
import com.example.springboot.springbootcrud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/orders")
    public Item createItem(@RequestBody Item item){
       return itemService.save(item);
    }

    @PutMapping("/orders/{id}")
    public void updateItem(@PathVariable(value = "id") Integer id,@RequestBody Item item){
        itemService.update(id,item);
    }

    @GetMapping("/orders")
    public List<Item> findAll(){
        return itemService.findAll();

    }
    @GetMapping("/orders/{id}")
    public Optional<Item> findById(@PathVariable(value = "id") Integer id){
        return itemService.findById(id);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id){
        itemService.deleteById(id);
    }
}
