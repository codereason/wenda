package com.learn.dao;

import com.learn.model.Comment;
import com.learn.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by hy on 2019/2/19.
 */
@Mapper
public interface MessageDAO {

//    //
//     `id` INT NOT NULL AUTO_INCREMENT,
//  `from_id` INT NULL,
//  `to_id` INT NULL,
//  `content` TEXT NULL,
//  `created_date` DATETIME NULL,
//  `has_read` INT NULL,
//  `conversation_id` VARCHAR(45) NOT NULL,
//
//    private int id;
//    private int from_id;
//    private int to_id;
//    private String content;
//    private Date createdDate;
//    private int hasRead;
//    private String conversationId;
    // 注意空格

    String TABLE_NAME = " message ";
    String INSERT_FIELDS = " from_id, to_id, content, created_date, has_read, conversation_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{fromId},#{toId},#{content},#{createdDate},#{hasRead},#{conversationId})"})
    int addMessage(Message message);



    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME,
            " where conversation_id=#{conversationId}  order by id desc limit #{offset},#{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String conversationId,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);


}
