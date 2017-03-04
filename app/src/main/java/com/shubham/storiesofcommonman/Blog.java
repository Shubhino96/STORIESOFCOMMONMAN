package com.shubham.storiesofcommonman;

/**
 * Created by SHUBHAM on 2/3/2017.
 */

public class Blog
{
    private String title;
    private String desc;
    private String image;
    private String uid;
    private String username;

    public Blog()
    {

    }

    public Blog(String title, String image, String desc,String username,String uid) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.username = username;
        this.uid =uid;

    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
