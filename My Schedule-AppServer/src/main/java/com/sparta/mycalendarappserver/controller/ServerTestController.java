package com.sparta.mycalendarappserver.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api") //api 한 방에 관리.

public class ServerTestController
{
    @GetMapping("/get")
    @ResponseBody //문자열 반환.
    public String GetTest()
    {
        return "Get_Test_Complete";
    }

    @PostMapping("/post")
    @ResponseBody
    public String PostTest()
    {
        return "Post_Test_Complete";
    }

    @PutMapping ("/put")
    @ResponseBody
    public String PutTest()
    {
        return "Put_Test_Complete";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String DeleteTest()
    {
        return "delete_Test_Complete";
    }

}

