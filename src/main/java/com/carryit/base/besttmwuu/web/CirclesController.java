package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.Circles;
import com.carryit.base.besttmwuu.service.BoardService;
import com.carryit.base.besttmwuu.service.CirclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/circle")
public class CirclesController {

    @Autowired
    CirclesService circlesService;
    @Autowired
    BoardService boardService;

    @RequestMapping("/getCircles")
    public List<Circles> getCircles(){
        List<Circles> circles = new ArrayList<>();

        circles  = circlesService.getCircles();
        if(circles.size()>0){
            for (Circles circle :circles) {
               List<Board> boardList = boardService.getBoardByCid(circle.getId());
                circle.setBoards(boardList);
            }
        }
        return circles;
    }

}
