package com.example.ramserver.mapper;

import com.example.ramserver.vo.BoardCheckVo;
import com.example.ramserver.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVo> boardList(int index);
    BoardVo boardDetail(int boardId);
    int register(BoardVo boardVo);
    int modify(BoardVo boardVo);
    int delete(int boardId);
    int check(BoardCheckVo boardCheckVo);
    int max();
}
