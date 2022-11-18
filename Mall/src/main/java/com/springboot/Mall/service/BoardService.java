package com.springboot.Mall.service;

import com.springboot.Mall.entity.Board;
import com.springboot.Mall.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    public Board getBoard(Long bno){
        Optional<Board> board = this.boardRepository.findById(bno);

        return board.get();
    }

    public void write(String title, String contents){
        Board board = new Board();
        board.setTitle(title);
        board.setContents(contents);
        board.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss")));
        boardRepository.save(board);
    }
}
