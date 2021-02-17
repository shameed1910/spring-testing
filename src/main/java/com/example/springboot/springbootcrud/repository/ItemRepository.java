package com.example.springboot.springbootcrud.repository;

import com.example.springboot.springbootcrud.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
