package com.example.__PostNotes.web;

import com.example.__PostNotes.po.Title;
import com.example.__PostNotes.service.LineService;
import com.example.__PostNotes.service.TitleService;
import javassist.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EditController {
    //透過 @RequestMapping 指定從/會被對應到此hello()方法
    @Autowired
    private LineService lineService;
    @Autowired
    private TitleService titleService;
    //搜尋 ok
    @GetMapping("/test/{id}")
    public String tags(@PathVariable Long id) throws Exception {
        JSONObject data = new JSONObject();
        data.put("title",titleService.getTitleById(id).orElseThrow(()-> new Exception("What Null")));

        return data.toString();
    }
    //修改by/id
    @PostMapping("/test/{id}")
    public String editPost(@Valid Title title, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) throws NotFoundException {
        Title title1 = titleService.getTitleByName(title.getName());
        if(title1 != null){
            result.rejectValue("name","nameError","不能添加重複的分類");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }

        Title t = titleService.updateTitle(id,title);
        if(t == null){
            attributes.addFlashAttribute("message","更新失敗");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/";
    }
    //新增和修改
    @PostMapping("/test")
    public String post(Title title, BindingResult result, RedirectAttributes attributes) throws NotFoundException {

//        title.setName("www");
//        title.setLife(true);
        Title t;
        if (title.getId() == null) {
            t=titleService.saveTitle(title);
            System.out.println("save\n");
        }else{
            t=titleService.updateTitle(title.getId(),title);
            System.out.println(title.getId().toString()+"update\n");
        }

        if(t==null){
            System.out.println("False\n");
        }else{
            System.out.println("True\n");
        }
        return "redirect:/";
    }
    //刪除 ok
    @GetMapping("/test/{id}/delete")
    public String delete(@PathVariable Long id){
        titleService.deleteTitle(id);
        return "400";
    }
}