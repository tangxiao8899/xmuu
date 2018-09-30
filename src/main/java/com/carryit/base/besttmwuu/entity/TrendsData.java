package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.util.List;

public class TrendsData implements Serializable {

    private Post post; //动态
    private List<Post> commentList;// 该动态下的评论
    private long praiseCount; //点赞数
    private List<String> avatarList;//点赞头像

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Post> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Post> commentList) {
        this.commentList = commentList;
    }

    public long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(long praiseCount) {
        this.praiseCount = praiseCount;
    }

    public List<String> getAvatarList() {
        return avatarList;
    }

    public void setAvatarList(List<String> avatarList) {
        this.avatarList = avatarList;
    }
}
