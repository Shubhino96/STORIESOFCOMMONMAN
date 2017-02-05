package com.shubham.storiesofcommonman;

/**
 * Created by SHUBHAM on 2/3/2017.
 */

public class Blog
{
  private String title;
    private String desc;
    private String image;

    public Blog()
    {

    }

    public Blog(String title, String image, String desc) {
        this.title = title;
        this.image = image;
        this.desc = desc;
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
}
