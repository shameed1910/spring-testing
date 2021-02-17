package com.example.springboot.springbootcrud.controller;

import com.example.springboot.springbootcrud.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {

   @Autowired
   private TestRestTemplate testRestTemplate;

    @Test
    @Sql("/test.sql")
    public void findByIdTest(){

    ResponseEntity<Item> response= testRestTemplate.getForEntity("/api/orders/1", Item.class);
    Item item=new Item(1,"Item1","Item1");
    Item responseItem=response.getBody();
    System.out.println("response item id:::::::"+responseItem.getId());
    Assertions.assertEquals(item.getId(), responseItem.getId());


    Assertions.assertEquals(1, response.getBody().getId());
    Assertions.assertEquals("Item1", response.getBody().getName());
    Assertions.assertEquals("Item1", response.getBody().getDescription());


    }
    

    
//  @Test
//  public void saveItemTest(){
//        Item item=new Item(2,"Item2","Item2");
//
//       // HttpEntity<Item> entity=new HttpEntity<>(item);
//        ///System.out.println("Helllo......."+entity.getBody().getId());
//
//        ResponseEntity<Item> response= testRestTemplate.postForEntity("/api/orders", item, Item.class);
//        System.out.println("Response......."+response.getBody().getId());
//
//        Assertions.assertEquals(1, response.getBody().getId());
//        Assertions.assertEquals("Item2", response.getBody().getName());
//        Assertions.assertEquals("Item2", response.getBody().getDescription());
//        Assertions.assertNotNull(response);
//        Assertions.assertNotNull(response.getBody());
//
//    }
    
    
//    @Test
//    @Sql("/test.sql")
//      public void updateItemTest(){
//
//            ResponseEntity<Item> response= testRestTemplate.getForEntity("/api/orders/1", Item.class);
//            
//            Item item=response.getBody();
//            item.setName("Item2");
//            item.setDescription("Item2");
//            
//            testRestTemplate.put("/api/orders/1", item);
//            
//            ResponseEntity<Item> updatedResponse= testRestTemplate.getForEntity("/api/orders/1", Item.class);
//           Item updatedItem= updatedResponse.getBody();
//
//            
//            Assertions.assertEquals(updatedItem.getName(), "Item2");
//            
//            
//
//        }
//    
  
// @Test
//@Sql("/test.sql")
//  public void deleteItemTest(){
//
//        ResponseEntity<Item> response= testRestTemplate.getForEntity("/api/orders/1", Item.class);
//        Assertions.assertNotNull(response);
//        Assertions.assertNotNull(response.getBody());
//
//        testRestTemplate.delete("/api/orders/1", Item.class);
//        
//        response= testRestTemplate.getForEntity("/api/orders/1", Item.class);
//        Assertions.assertNull(response.getBody());
//    }
 
 
}
