package com.learn.dao;

import com.learn.model.Comment;
import com.learn.model.LoginTicket;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by hy on 2019/2/19.
 */
@Mapper
public interface CommentDao {

    // 注意空格
    String TABLE_NAME = " comment ";
    String INSERT_FIELDS = " user_id, content, created_date, entity_id,entity_type,status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId},#{createdDate},#{entityId},#{entityType},#{status})"})
    int addComment(Comment comment);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where entity_id=#{entityId} and entity_type=#{entityType} order " +
            "by created_date desc}"})
    List<Comment> selectCommentsByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

    @Select({"select count(id)  from ", TABLE_NAME, " where entity_id=#{entityId} and entity_type=#{entityType}"})
    int  getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);

    @Update({"update ", TABLE_NAME, " set status=#{status} where ticket=#{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
}
