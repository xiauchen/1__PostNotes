package com.example.__PostNotes.service;

import com.example.__PostNotes.po.Title;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TitleService {
    Title saveTitle(Title title);

    Title getTitle(Long id);

    Title getTitleByContext(String name);

    Page<Title> listTitle(Pageable pageable);

    List<Title> listTitle();

    List<Title> listTitle(String ids);

//    List<Title> listTitleTop(Integer size);

    Title updateTitle(Long id, Title title) throws NotFoundException;

    void deleteTitle(Long id);

    Title findOne();

    List<Title> listTitleTop(Integer size);

    Title getTitleByName(String name);
    Optional<Title> getTitleById(Long id);

//    Page<Title> listBlog(String query,Pageable pageable);
//
//    public Title getAndConvert(Long id) throws NotFoundException;
}
