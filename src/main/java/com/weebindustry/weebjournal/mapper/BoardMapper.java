package com.weebindustry.weebjournal.mapper;

import java.util.List;

import com.weebindustry.weebjournal.dto.BoardDTO;
import com.weebindustry.weebjournal.models.Board;
import com.weebindustry.weebjournal.models.Post;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(board.getPosts()))")
    BoardDTO mapBoardToDTO(Board board);
    
    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Board mapDTOToBoard(BoardDTO boardDTO);
}
