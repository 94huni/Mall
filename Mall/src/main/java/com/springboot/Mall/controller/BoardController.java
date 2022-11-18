package com.springboot.Mall.controller;

import com.springboot.Mall.dto.BoardWriteDto;
import com.springboot.Mall.entity.Board;
import com.springboot.Mall.entity.User;
import com.springboot.Mall.service.BoardService;
import com.springboot.Mall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/board/list")
    public String boardList(Model model){

        List<Board> boardList = boardService.getList();
        model.addAttribute("BoardList", boardList);

        return "board/list";
    }

    @GetMapping("/board/list/{bno}")
    public String getBoard(@PathVariable Long bno, Model model){
        Board getBoard = boardService.getBoard(bno);
        model.addAttribute("getBoard", getBoard);

        return "board/view";
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("/board/write")
    public String writeBoard(Principal principal, Model model) throws Exception {
        User user = userService.getUser(Long.valueOf(principal.getName()));
        userService.getUser(user.getUId());
        model.addAttribute("user", user);

        return "board/write";
    }
    @PreAuthorize("isAuthenticated")
    @PostMapping("/board/write")
    public String write(BoardWriteDto boardWriteDto){
        this.boardService.write(boardWriteDto.getTitle(), boardWriteDto.getContents());
        return "redirect:/board/list";
    }



}
