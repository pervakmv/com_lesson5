package com.lesson5;

import com.lesson5.Exception.InternalServerErrorException;


import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@Controller
@Transactional
@RequestMapping(value = "/item", headers = "Accept=*/*", produces = "application/json")
public class ItemController {

    private ItemDAO dao;

    @Autowired
    public ItemController(ItemDAO repository) {
        this.dao = repository;
    }


    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<String> save(@RequestBody Item item) {
        dao.save(item);
        return new ResponseEntity("Ok", HttpStatus.OK);
    }


//    @PostMapping(value = "/delete", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    //http://localhost:8080/item/delete/241
    @GetMapping(value = "/delete/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable("id") String id) {
        System.out.println(dao.delete(Long.parseLong(id)));
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    //http://localhost:8080/item/id?id=241
    @GetMapping(value = "/id", produces = {MediaType.TEXT_PLAIN_VALUE})
    // @RequestMapping(method = RequestMethod.GET, value = "/id", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> readId(@RequestParam String id, Model model) {
        System.out.println("ID = " + id);

        return new ResponseEntity("Ok", HttpStatus.OK);
    }


    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<String> update(@RequestBody Item item) {
        System.out.println("Item " + item.toString());
        dao.update(item);
        return new ResponseEntity("Ok", HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/list", produces = "text/plain")
    public @ResponseBody
    String listItem() {
        List<String> result;
        result = dao.listItem();
        return result.toString();
    }


    @GetMapping(value = "/test", produces = {MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody
    String listTest() {
        System.out.println("Test ok");
        return "TEST OK";
    }


    @PostMapping(value = "/test2", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<String> handleJsonPosRequest(@RequestBody Car car) {
        System.out.println("Car = " + car);
        return new ResponseEntity("/register", HttpStatus.OK);
    }


    @PostMapping(value = "/test3", produces = {MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody
    ResponseEntity<String> test2(@RequestBody String car) {
        System.out.println("String car " + car);
        ObjectMapper objectMapper = new ObjectMapper();
        Car newCar = new Car();
        try {
            newCar = objectMapper.readValue(car, Car.class);
        } catch (JsonProcessingException e) {
            System.err.println("Exception Occured while converting the Json into java " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Exception Occured while converting the Json into java " + e.getMessage());
        }

        System.out.println(newCar.toString());

        return new ResponseEntity("/item/test3", HttpStatus.OK);

    }


}
