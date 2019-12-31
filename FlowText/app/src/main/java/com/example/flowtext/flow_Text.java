package com.example.flowtext;

public class flow_Text {
    public int char_count;
    public int width;
    public double flow_text;

    public flow_Text(int char_count, int width, double flow_text) {
        this.char_count = char_count;
        this.width = width;
        this.flow_text = flow_text;
    }

    public int getChar_count() {
        return char_count;
    }

    public void setChar_count(int char_count) {
        this.char_count = char_count;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getFlow_text() {
        return flow_text;
    }

    public void setFlow_text(double flow_text) {
        this.flow_text = flow_text;
    }
}
