package com.example.__PostNotes.dao;

import com.example.__PostNotes.po.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TitleRepository extends JpaRepository<Title,Long> {
    @Query("select t from Title t")
    List<Title> findTop(Pageable pageable) ;

    Title findByName(String name);


    Title findOneById(Long id);

}
