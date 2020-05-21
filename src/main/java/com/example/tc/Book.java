package com.example.tc;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "author",
        "year",
        "isbn"
})
public class Book {

    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("year")
    private String year;
    @JsonProperty("isbn")
    private String isbn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    public static Comparator<Book> AuthorComparator
            = new Comparator<Book>() {

        public int compare(Book book1, Book book2) {

            String author1 = book1.getAuthor().toUpperCase();
            String author2 = book2.getAuthor().toUpperCase();

            return author1.compareTo(author2);
        }

    };

    public static Comparator<Book> TitleComparator
            = new Comparator<Book>() {

        public int compare(Book book1, Book book2) {

            String title1 = book1.getTitle().toUpperCase();
            String title2 = book2.getTitle().toUpperCase();

            return title1.compareTo(title2);
        }
    };

    public static Comparator<Book> YearComparatorAsc
            = new Comparator<Book>() {

        public int compare(Book book1, Book book2) {

            String year1 = book1.getYear();
            String year2 = book2.getYear();

            if(year1 == null)
            {
                return 1;
            }else if (year2 == null)
            {
                return -1;
            }else if(year1 == null && year2 == null)
            {
                return 0;
            }
            return year1.compareTo(year2);
        }
    };

    public static Comparator<Book> YearComparatorDesc
            = new Comparator<Book>() {

        public int compare(Book book1, Book book2) {
            String year1 = book1.getYear();
            String year2 = book2.getYear();
            if(year1 == null)
            {
                return 1;
            }else if (year2 == null)
            {
                return -1;
            }else if(year1 == null && year2 == null)
            {
                return 0;
            }
            return year2.compareTo(year1);
        }

    };


}