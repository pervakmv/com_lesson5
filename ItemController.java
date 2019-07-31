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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Transactional
@RequestMapping("/item")
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

    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    @Produces({"application/xml", "application/json"})

    @PostMapping(value = "/save")
    public @ResponseBody
    ResponseEntity<String> save(@RequestBody Item item){
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        objectMapper.setDateFormat(df);
        System.out.println("Body item " + item);
        System.out.println("Name " + item.getName());
        Item newItem =new Item();

//        try {
//            newItem = objectMapper.readValue(newItem, Item.class);
//
//
//        }catch(JsonProcessingException e){
//            System.out.println("Exception Occured while converting the Json into java " + e.getMessage());
//        }catch(IOException e){
//            System.out.println("Exception Ocuured while converting the Json into java " + e.getMessage());
//        }
//        System.out.println(newItem.toString());


       // dao.save(item);


        return new ResponseEntity("/Item/Save", HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/listItem", produces = "text/plain")
//    public @ResponseBody
//    void listItem() throws InternalServerErrorException{
//        if(dao != null)
//        System.out.println("Items : " + dao.listItem());
//        else
//            System.out.println("dao is null");
//    }



       //@RequestMapping(method = RequestMethod.GET, value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE)


   @Consumes({MediaType.APPLICATION_JSON_VALUE})
    @Produces({"application/xml", "application/json"})
    @GetMapping(value = "/test")
    public @ResponseBody
    ResponseEntity<String> listTest(@ModelAttribute Car car) {

        System.out.println("car =" + car.toString());
        System.out.println("Test ok");
        return new ResponseEntity("/test", HttpStatus.OK);


    }
}
