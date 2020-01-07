package com.example.__PostNotes.service;

import com.example.__PostNotes.po.Line;
import com.example.__PostNotes.po.Title;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LineServiceImpl implements LineService{
    @Autowired
    private com.example.__PostNotes.dao.LineRepository LineRepository;

    @Transactional
    @Override
    public Line saveLine(Line line) {
        return LineRepository.save(line);
    }

    @Transactional
    @Override
    public Line getLine(Long id) {
        return LineRepository.findById(id).orElse(null);
    }

    @Override
    public Line getLineByContext(String name) {
        return LineRepository.findByContext(name);
    }

    @Transactional
    @Override
    public Page<Line> listLine(Pageable pageable) {
        return LineRepository.findAll(pageable);
    }

    @Override
    public List<Line> listLine() {
        return LineRepository.findAll();
    }


    @Override
    public List<Line> listLine(String ids) { //1.2.3
        return LineRepository.findAllById(converToList(ids));
    }

//    @Override
//    public List<Line> listLineTop(Integer size) {
//        Sort sort=null;
//        Pageable pageable = PageRequest.of(0,size, sort.by(Sort.Direction.DESC,"blogs.size"));;
//        return LineRepository.findTop(pageable);
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
    public Line updateLine(Long id, Line line) throws NotFoundException {
        Line t = LineRepository.findById(id).orElse(null);
        if(t == null){
            throw new NotFoundException("不存在該標簽");
        }

        return LineRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteLine(Long id) {
        LineRepository.deleteById(id);
    }
}
