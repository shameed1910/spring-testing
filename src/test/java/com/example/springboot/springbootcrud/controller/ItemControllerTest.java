package com.example.springboot.springbootcrud.controller;

import com.example.springboot.springbootcrud.model.Item;
import com.example.springboot.springbootcrud.repository.ItemRepository;
import com.example.springboot.springbootcrud.service.ItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemControllerTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    Item item1=null;
    Item item2=null;

    @BeforeEach
    public void setup(){
         item1=new Item(1,"Item1","Item1");
         item2=new Item(2,"Item2","Item2");
    }

    @Test
    @DisplayName("Test findById not found")
    public void findById() {
        //setup mock repository
        doReturn(Optional.of(item1)).when(itemRepository).findById(1);

        Optional<Item> expectedItem= itemService.findById(1);

        Assertions.assertTrue(expectedItem.isPresent(),"Item was not found");
        Assertions.assertSame(expectedItem.get(),item1,"The Item returned was not same as the mock");
    }

    @Test
    void testFindAll(){

        doReturn(Arrays.asList(item1, item2)).when(itemRepository).findAll();

        List<Item> items= itemService.findAll();
        Assertions.assertEquals(2,items.size(),"FindAll should return 2 items");
    }

    @Test
    void testSave(){
        doReturn(item1).when(itemRepository).save(any());

        Item savedItem=itemRepository.save(item1);
        Assertions.assertNotNull(savedItem,"The saved item should not be null");
        Assertions.assertEquals(item1.getId(),savedItem.getId(),"The item id should be matched");
    }

    @Test
    void testUpdate(){
        doReturn(item1).when(itemRepository).save(any());

        Optional<Item> optionalItem= itemService.findById(1);
        optionalItem.ifPresent(updateItem-> {
            updateItem.setName("Item2");
            updateItem.setDescription("Item2");
            Assertions.assertEquals(item1.getId(),updateItem.getId());

        });
    }

    @Test
    void testDelete(){

        Optional<Item> optionalItem= itemService.findById(1);
        optionalItem.ifPresent(deleteItem-> {
            itemRepository.deleteById(deleteItem.getId());
            Assertions.assertNull(deleteItem);
        });

    }


    @AfterEach
   public void tearDown(){
        item1=new Item(0,null,null);
        item2=new Item(0,null,null);
    }
}