package com.ben.java.springboot.domain;

import javax.persistence.*;

@Entity
@Table(name = "chatroom_info")
public class ChatroomInfo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int roomId;
    @Column
    private String roomName;
    @Column
    private String roomBgimg;
    @Column
    private String roomCover;
    @Column
    private String roomNotice;
    @Column
    private int peopleNum;
    @Column
    private int totalPeopleNum;
    @Column
    private int roomStatusCode;
    @Column
    private int uid;
    @Column
    private String nickName;
    @Column
    private String rtmpPushUrl;
    @Column
    private String rtmpPullUrl;
    @Column
    private String playBackUrl;
    @Column
    private String messageQueueId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomBgimg() {
        return roomBgimg;
    }

    public void setRoomBgimg(String roomBgimg) {
        this.roomBgimg = roomBgimg;
    }

    public String getRoomCover() {
        return roomCover;
    }

    public void setRoomCover(String roomCover) {
        this.roomCover = roomCover;
    }

    public String getRoomNotice() {
        return roomNotice;
    }

    public void setRoomNotice(String roomNotice) {
        this.roomNotice = roomNotice;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getTotalPeopleNum() {
        return totalPeopleNum;
    }

    public void setTotalPeopleNum(int totalPeopleNum) {
        this.totalPeopleNum = totalPeopleNum;
    }

    public int getRoomStatusCode() {
        return roomStatusCode;
    }

    public void setRoomStatusCode(int roomStatusCode) {
        this.roomStatusCode = roomStatusCode;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRtmpPushUrl() {
        return rtmpPushUrl;
    }

    public void setRtmpPushUrl(String rtmpPushUrl) {
        this.rtmpPushUrl = rtmpPushUrl;
    }

    public String getRtmpPullUrl() {
        return rtmpPullUrl;
    }

    public void setRtmpPullUrl(String rtmpPullUrl) {
        this.rtmpPullUrl = rtmpPullUrl;
    }

    public String getPlayBackUrl() {
        return playBackUrl;
    }

    public void setPlayBackUrl(String playBackUrl) {
        this.playBackUrl = playBackUrl;
    }

    public String getMessageQueueId() {
        return messageQueueId;
    }

    public void setMessageQueueId(String messageQueueId) {
        this.messageQueueId = messageQueueId;
    }
}
