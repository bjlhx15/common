package com.github.bjlhx15.core.http.json;

import com.github.bjlhx15.core.http.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("json")
@RequestMapping(value = "/json", method = RequestMethod.POST)
public class PostController {

    @RequestMapping("/noparam")
    @ResponseBody
    public Object noparam() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "2000");
        return map;
    }

    @RequestMapping("/params")
    @ResponseBody
    public Object params(String id, String msg,String name) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "2000");
        map.put("id", id);
        map.put("message", msg);
        map.put("name", name);
        return map;
    }

    @RequestMapping("/params1")
    @ResponseBody
    public Object params1(@RequestParam("id")String id, @RequestParam("msg") String message, String name) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "2000");
        map.put("id", id);
        map.put("message", message);
        map.put("name", name);
        return map;
    }

    @RequestMapping("/paramsobject")
    @ResponseBody
    public Object paramsobject(Person person) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "2000");
        map.put("person", person);
        return map;
    }

    /**
     * 客户端 访问失败
     * curl -X POST \
     *   http://localhost:8080/form/paramsobject1 \
     *   -H 'content-type: application/x-www-form-urlencoded' \
     *   -d 'name=%E5%BC%A0%E4%B8%89&age=14'
     * @param person
     * @return
     */
    @RequestMapping("/paramsobject1")
    @ResponseBody
    public Object paramsobject1(@RequestBody Person person) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "2000");
        map.put("person", person);
        return map;
    }
}
