package com.example.__PostNotes.web;

import com.example.__PostNotes.po.Line;
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
        return "Hello Docker";
    }
    @GetMapping("/All")
    public String all() throws JSONException {
        JSONObject data = new JSONObject();
        List<Title> t=titleService.listTitle();
        int i=1;
        for (Title t1:t
        ) {
            data.put("title"+i,t1);
            data.accumulate("title"+i,lineService.listLineByTitleId(t1.getId()));
            i++;
        }
        return data.toString();
    }
    //獲取byId ok
    @GetMapping("/{id}")
    public String tags(@PathVariable Long id) throws Exception {
        JSONObject data = new JSONObject();
        data.put("title",titleService.getTitleById(id).orElseThrow(()-> new Exception("What Null")));
        data.accumulate("title",lineService.listLineByTitleId(id));
        return data.toString();
    }
    //刪除 ok
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        titleService.deleteTitle(id);
        return "400";
    }
    //搜尋
    @PostMapping("/search")
    public String search(@PageableDefault(size = 7,sort ={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) throws JSONException {
        JSONObject data = new JSONObject();
        List<Title> t=titleService.listTitle("%"+query+"%",pageable);
        int i=1;
        for (Title t1: t
             ) {
            data.put("title"+i,t1);
            data.accumulate("title"+i,lineService.listLineByTitleId(t1.getId()));
            i++;
        }
        return data.toString();
    }
}