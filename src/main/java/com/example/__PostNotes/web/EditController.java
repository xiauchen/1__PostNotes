package com.example.__PostNotes.web;

import com.example.__PostNotes.po.Line;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class EditController {
    //透過 @RequestMapping 指定從/會被對應到此hello()方法
    @Autowired
    private LineService lineService;
    @Autowired
    private TitleService titleService;
    //新增和修改 line添加未完成
    @PostMapping("/edit")
    public String postTitle(Title title, BindingResult result, RedirectAttributes attributes) throws NotFoundException {
        Title t;
        if (title.getId() == null) {
            t=titleService.saveTitle(title);
//            l=lineService.saveLine(line);
            System.out.println("save\n");
        }else{
            t=titleService.updateTitle(title.getId(),title);
//            l=lineService.saveLine(line);
            System.out.println(title.getId().toString()+" update\n");
        }
        if(t==null){
            System.out.println("False\n");
            return "404";
        }else{
            System.out.println("True\n");
            return "400";
        }
    }
    //更改完成狀態life
    @PostMapping("/{id}/life")
    public String postLife(@PathVariable Long id,@RequestParam String check) throws Exception {
        Title t = titleService.getTitle(id);
        //功能1：便利貼已完成
        if(check == "0") {
            t.setLife(false);
        }else if(check == "1"){
            t.setLife(true);
        }
        titleService.updateTitle(id,t);
        System.out.println(t.getId().toString()+" lifeUpdate\n");
        if(t==null){
            System.out.println("False\n");
            return "404";
        }else{
            System.out.println("True\n");
            return "400";
        }
    }
    //
//    @PostMapping("/comments")
//    public String post(Comment comment, HttpSession session){
//        Long blogId=comment.getBlog().getId();
//        comment.setBlog(blogService.getBlog(blogId));
//        User user = (User) session.getAttribute("user");
//        if(user != null){
//            comment.setAvatar(user.getAvatar());
//            comment.setAdminComment(true);
//        }else{
//            comment.setAvatar(avatar);
//        }
//
//        commentService.saveComment(comment);
//        return "redirect:/comments/" + blogId;
//    }
    @PostMapping("/line")
    public String postLine(Line line, HttpSession session) throws NotFoundException {
        Line l;
        if (line.getId() == null) {
            l =  lineService.saveLine(line);
        } else {
            l = lineService.updateLine(line.getId(), line);
        }
        if(l == null){
            System.out.println("message false");
            return "404";
        }else{
            System.out.println("message complete");
            return "400";
        }
    }
}