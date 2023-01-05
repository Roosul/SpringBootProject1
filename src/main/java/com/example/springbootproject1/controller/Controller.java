package com.example.springbootproject1.controller;

import com.example.springbootproject1.service.Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.*;

import static com.example.springbootproject1.MyJsonUtils.getInteger;
import static com.example.springbootproject1.MyJsonUtils.getString;

@RestController
@RequestMapping(value = "/main")
public class Controller {
    final Service service = new Service();
    private final JsonParser parser = new JsonParser();
    @PostMapping(value="/add")
    public  String add(@RequestBody String body) {
        JsonObject jo = parser.parse(body).getAsJsonObject();
        String name = getString(jo, "name");
        String authorName = getString(jo, "authorName");
        Integer pageCount = getInteger(jo, "pageCount");
        return service.addBook(name,authorName,pageCount);
    }
    //TODO создать ветку получения книги
    //TODO создать ветку удаления и изменения книги




}
