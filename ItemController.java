package com.lesson5;

import com.lesson5.Exception.InternalServerErrorException;


import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Transactional
@RequestMapping(value = "/item", headers = "Accept=*/*", produces = "application/json")
public class ItemController {

    private Repository dao;

    @Autowired
    public ItemController(Repository repository) {
        this.dao = repository;
    }

    //    private ItemDAO dao;
//
//    @Autowired
//    public TestController(ItemDAO dao) {
//        this.dao = dao;
//    }
//
//    public ItemDAO getDao() {
//        return dao;
//    }
//
//    public void setDao(ItemDAO dao) {
//        this.dao = dao;
//    }


    //
    //@RequestMapping(method = RequestMethod.GET, value = "/item/save", produces = "text/plain")



    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<String> save(@RequestBody Item item) {
        System.out.println("Body item " + item);
        System.out.println( item.toString());

        dao.save(item);


        return new ResponseEntity("/Item/Save", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listItem", produces = "text/plain")
    public @ResponseBody
    void listItem() throws InternalServerErrorException{
        if(dao != null)
        System.out.println("Items : " + dao.listItem());
        else
            System.out.println("dao is null");
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
