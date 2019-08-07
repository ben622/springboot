package com.ben.java.springboot.repository;

import com.ben.java.springboot.domain.ChatroomInfo;
import com.ben.java.springboot.domain.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * 直播间
 */
public interface ChatroomRepository extends CrudRepository<ChatroomInfo, Integer> {

    ChatroomInfo findChatroomInfoByUid(int uid);

}
