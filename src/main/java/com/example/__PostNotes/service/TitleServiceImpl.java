package com.example.__PostNotes.service;

import com.example.__PostNotes.dao.TitleRepository;
import com.example.__PostNotes.po.Title;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.Collections.sort;

@Service
public class TitleServiceImpl implements TitleService{
    @Autowired
    private TitleRepository titleRepository;

    @Transactional
    @Override
    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    @Transactional
    @Override
    public Title getTitle(Long id) {
        return titleRepository.findById(id).orElse(null);
    }

    @Override
    public Title getTitleByContext(String name) {
        return titleRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Title> listTitle(Pageable pageable) {
        return titleRepository.findAll(pageable);
    }

    //進行update 排序
    @Override
    public List<Title> listTitle() {
//        return titleRepository.findAll().sort(Comparator.comparing(o -> o.getUpdateTime()));
        List<Title> tSort = titleRepository.findAll();
        tSort.sort(new Comparator<Title>() {
            @Override
            public int compare(Title title, Title t1) {
                return t1.getUpdateTime().compareTo(title.getUpdateTime());
            }
        });
        return tSort;
    }
//list.sort(Comparator.comparing(o -> o.getDateTime()));


    @Override
    public List<Title> listTitle(String ids) { //1.2.3
        return titleRepository.findAllById(converToList(ids));
    }

    @Override
    public Title findOne(){
        return titleRepository.findOneById(Long.parseLong("1"));
    }


    @Override
    public List<Title> listTitleTop(Integer size) {
        Sort sort=null;
        Pageable pageable = PageRequest.of(0,size, sort.by(Sort.Direction.DESC,"blogs.size"));;
        return titleRepository.findTop(pageable);
    }

    @Override
    public Title getTitleByName(String name) {
        return null;
    }
//    @Override
//    public Page<Title> listBlog(String query, Pageable pageable) {
//        return titleRepository.findByQuery(query,pageable);
//    }

//    @Transactional
//    @Override
//    public Title getAndConvert(Long id) throws NotFoundException {
//        Title title = titleRepository.findById(id).orElse(null);
//        if(title == null){
//            throw new NotFoundException("資源不存在");
//        }
//        Title t = new Title();
//        BeanUtils.copyProperties(title,t);
//        String content = t.getContent();
//        titleRepository.updateViews(id);
//        return t;
//    }

    private List<Long> converToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids!=null){
            String[] idarray = ids.split(",");
            for(int i=0;i< idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Title updateTitle(Long id, Title title) throws NotFoundException {
        Title t = titleRepository.findById(id).orElse(null);
        if(t == null){
            throw new NotFoundException("不存在該標簽");
        }

        return titleRepository.save(title);
    }

    @Transactional
    @Override
    public void deleteTitle(Long id) {
        titleRepository.deleteById(id);
    }

    @Override
    public Optional<Title> getTitleById(Long id){ return titleRepository.findById(id);}

    @Override
    public List<Title> listTitle(String query, Pageable pageable) {

        return titleRepository.findByQuery(query,pageable);
    }

}
