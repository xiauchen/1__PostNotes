package com.example.__PostNotes.web;

import com.example.__PostNotes.po.Title;
import com.example.__PostNotes.service.LineService;
import com.example.__PostNotes.service.TitleService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javassist.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    //透過 @RequestMapping 指定從/會被對應到此hello()方法
    @Autowired
    private LineService lineService;
    @Autowired
    private TitleService titleService;
    public IndexController() {
    }
    //主頁
    @GetMapping("/")
    public String index() throws JSONException {
        JSONObject data = new JSONObject();

        data.put("title",titleService.listTitle());
        data.put("line",lineService.listLine());

        System.out.println(lineService.listLine());
        return data.toString();

    }

}