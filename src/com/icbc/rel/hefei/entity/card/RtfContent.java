package com.icbc.rel.hefei.entity.card;

/**
 * @author LLF
 * @date 2019/10/15 - 15:49
 */
public class RtfContent {
    private String name;
    private String bg;
    private String wish;
    private String writeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getWriteName() {
        return writeName;
    }

    public void setWriteName(String writeName) {
        this.writeName = writeName;
    }

    public RtfContent(String name, String bg, String wish, String writeName) {
        this.name = name;
        this.bg = bg;
        this.wish = wish;
        this.writeName = writeName;
    }
}
