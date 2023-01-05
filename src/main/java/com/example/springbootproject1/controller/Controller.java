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

    @PostMapping(value = "/add")
    public String add(@RequestBody String body) {
        JsonObject jo = parser.parse(body).getAsJsonObject();
        String name = getString(jo, "name");
        String authorName = getString(jo, "authorName");
        Integer pageCount = getInteger(jo, "pageCount");
        return service.addBook(name, authorName, pageCount);
    }
    @GetMapping(value = "/getName")
    public String getName(@RequestBody String body) {
        JsonObject jo = parser.parse(body).getAsJsonObject();
        String name = getString(jo, "name");
        return service.getBookByName(name);



    }
    @GetMapping(value = "/get")
    public String getBookId(@RequestBody String body){
        JsonObject jo = parser.parse(body).getAsJsonObject();
        Integer id = getInteger(jo, "id");
        return service.getBook(id);

    }
    @GetMapping(value = "/getAll")
    public String getAllBook() {
        return service.getAllBook();
    }
    @DeleteMapping(value = "/delete")
    public String delete(@RequestBody String body){
        JsonObject jo = parser.parse(body).getAsJsonObject();
        String name = getString(jo, "name");
        return service.deleteBook(name);

    }
    @PutMapping(value = "/put")
    public String putBook(@RequestBody String body){
        JsonObject jo = parser.parse(body).getAsJsonObject();
        Integer id = getInteger(jo, "id");
        String name = getString(jo, "name");
        String authorName = getString(jo,"authorName");
        return service.putBook(id,name,authorName);
    }


}
