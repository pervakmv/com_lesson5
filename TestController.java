package com.lesson5;

import com.lesson5.Exception.InternalServerErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@Transactional
public class TestController {

    private Repository dao;

    @Autowired
    public TestController(Repository repository) {
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


    @RequestMapping(method = RequestMethod.GET, value = "/save-item", produces = "text/plain")
    public @ResponseBody
    String saveOrder() throws InternalServerErrorException {
        Item item = new Item();
        item.setDescription("Polymetal");
        item.setName("Dry Drum");
        item.setDateCreated(new Date());
        item.setLastUpdatedDate(new Date());
        dao.save(item);

        return "ok-k";
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/listItem", produces = "text/plain")
//    public @ResponseBody
//    void listItem() throws InternalServerErrorException{
//        if(dao != null)
//        System.out.println("Items : " + dao.listItem());
//        else
//            System.out.println("dao is null");
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "text/plain")
    public @ResponseBody
    String liistTest() {
        System.out.println("Test ok");
        return "test ok";
    }
}
