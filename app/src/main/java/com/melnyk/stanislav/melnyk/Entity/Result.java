package com.melnyk.stanislav.melnyk.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("books")
    private List<Book> books = null;

    public List<Book> getBooks() {
        return books;
    }


}