package com.example.springbootproject1;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.*;

import static com.example.springbootproject1.MyJsonUtils.getInteger;
import static com.example.springbootproject1.MyJsonUtils.getString;

@RestController
@RequestMapping(value = "/main")
public class Controller {
    private final JsonParser parser = new JsonParser();
    @PostMapping(value="/add")
    public  String add(@RequestBody String body){
        JsonObject jo = parser.parse(body).getAsJsonObject();
        String name = getString(jo,"name");
        String authorName = getString(jo,"authorName");
        Integer pageCount = getInteger(jo,"pageCount");
        return name+"  "+authorName+"  "+pageCount;
    }
    //TODO создать Staiging перенести эти изменения на него:
    //TODO создать класс в отдельной ветке,класса репозитория и книги hashMap
    //TODO создать ветку createBook
    //TODO создать ветку получения книги
    //TODO создать ветку удаления и изменения книги




}
