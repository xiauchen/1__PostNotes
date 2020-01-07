package com.example.__PostNotes.dao;

import com.example.__PostNotes.po.Line;
import com.example.__PostNotes.po.Title;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineRepository extends JpaRepository<Line,Long> {
    Line findByContext(String name);

//    @Query("select l from Line l")
//    List<Line> findTop(Pageable pageable);

}
