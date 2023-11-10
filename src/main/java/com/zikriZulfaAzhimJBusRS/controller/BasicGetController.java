package com.zikriZulfaAzhimJBusRS.controller;

import com.zikriZulfaAzhimJBusRS.Account;
import com.zikriZulfaAzhimJBusRS.Algorithm;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonAutowired;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonTable;
import com.zikriZulfaAzhimJBusRS.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
public interface BasicGetController<T extends Serializable> {
    JsonTable<T> getJsonTable();
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
                @RequestParam(value = "page", defaultValue = "0") int page,
                @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, a->true);
    }

    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
      return Algorithm.<T>find(getJsonTable(), e -> e.id==id);
    }

}
