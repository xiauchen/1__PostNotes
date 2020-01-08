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
    public String post(Title title, BindingResult result, RedirectAttributes attributes) throws NotFoundException {
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
    public String tags(Title title,@PathVariable Long id,@RequestParam String query) throws Exception {
        Title t;
        //功能1：便利貼已完成
        title.setLife(false);
        t=titleService.updateTitle(title.getId(),title);
        System.out.println(title.getId().toString()+" update\n");
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
    public String post(Line line, HttpSession session){
        Long titleId=line.getTitle().getId();
        line.setTitle(titleService.getTitle(titleId));
        lineService.saveLine(line);
        return "redirect:/comments/" + titleId;
    }
}