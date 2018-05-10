package org.imooc.controller;

import org.imooc.dto.CommentDto;
import org.imooc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/search")
    public String search(Model model, CommentDto commentDto){

        /*
        * 这个地方是要写评论表的controller实现的方法
        * */

        return "/content/commentList";
    }




}
