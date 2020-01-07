package com.example.__PostNotes.service;

import com.example.__PostNotes.po.Line;
import com.example.__PostNotes.po.Title;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LineService {
    Line saveLine(Line line);

    Line getLine(Long id);

    Line getLineByContext(String name);

    Page<Line> listLine(Pageable pageable);

    List<Line> listLine();

    List<Line> listLine(String ids);

//    List<Line> listLineTop(Integer size);

    Line updateLine(Long id, Line Line) throws NotFoundException;

    void deleteLine(Long id);
}
