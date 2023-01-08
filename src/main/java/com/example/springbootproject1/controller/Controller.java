package com.example.springbootproject1.controller;

import com.example.springbootproject1.RestError;
import com.example.springbootproject1.service.Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.springbootproject1.MyJsonUtils.*;

@RestController
@RequestMapping(value = "/main")
public class Controller {
    private static Logger log = LoggerFactory.getLogger(Controller.class);
    final Service service = new Service();
    private final JsonParser parser = new JsonParser();

    @PostMapping(value = "/add")
    public String add(@RequestBody String body) {
        JsonObject jo = parser.parse(body).getAsJsonObject();
        Integer id = getInteger(jo, "id");
        String name = getString(jo, "name");
        String authorName = getString(jo, "authorName");
        Integer pageCount = getInteger(jo, "pageCount");
        return service.addBook(id, name, authorName, pageCount);
    }

    @GetMapping(value = "/getName")
    public String getName(@RequestBody String body) {
        JsonObject jo = parser.parse(body).getAsJsonObject();
        String name = getString(jo, "name");
        return service.getBookByName(name);


    }

    @GetMapping(value = "/get")
    public String getBookId(@RequestParam int id) throws Exception {
        return (service.getBook(id).toString());
          }


    @GetMapping(value = "/getAll")
    public String getAllBook() {
        return service.getAllBook();
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestBody String body) {
        JsonObject jo = parser.parse(body).getAsJsonObject();
        Integer id = getInteger(jo, "id");
        if (service.deleteBook(id) == 1)
            return "Успешно удалено";
        else
            return "Ошибка методов";
    }

    @PutMapping(value = "/put")
    public String putBook(@RequestBody String body) {
        JsonObject jo = parser.parse(body).getAsJsonObject();
        Integer id = getInteger(jo, "id");
        String name = getString(jo, "name");
        String authorName = getString(jo, "authorName");
        String newParam = getString(jo,"newParam");

        if (id == null)
            return "Надо ввести id книги для внесения изменения";
        if (name == null && authorName == null)
            return "Надо ввести хотя бы один параметр из двух";
        return service.putBook(id, authorName, name,newParam);
    }

    @ExceptionHandler(Exception.class)
    public String handler(Exception e) {
        log.info(e.getMessage());
        return e.getMessage();
    }
    }
    //TODO прологировать вхолные данные
//TODO изучить изменения в коде
//TODO jpa and ORM
