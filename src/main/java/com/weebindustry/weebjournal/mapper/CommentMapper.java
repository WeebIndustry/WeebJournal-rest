package com.weebindustry.weebjournal.mapper;

import com.weebindustry.weebjournal.dto.CommentDTO;
import com.weebindustry.weebjournal.models.Comment;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentDTO commentDTO, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDTO mapToDto(Comment comment);
}
