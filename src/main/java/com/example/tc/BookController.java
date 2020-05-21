package com.example.tc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@Controller
public class BookController {
    Book[] booksArray;

    public BookController() {
        try {
            String url = "https://servicepros-test-api.herokuapp.com/api/v1/books";
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestMethod("GET");
            int response = conn.getResponseCode();
            if (response == 200) {
                Scanner responseReader = new Scanner(conn.getInputStream());
                StringBuffer buffer = new StringBuffer();
                while (responseReader.hasNextLine()) {
                    buffer.append(responseReader.nextLine() + "\n");
                }
                responseReader.close();

                booksArray = new ObjectMapper().readValue(buffer.toString(), Book[].class);
            }
        }catch(Exception e){
            System.out.println("Call To API Failed");
        }
    }
    @GetMapping("/")
    public String indexForm(Model model){
        return "index";
    }

    @GetMapping("/filter")
    public String filterForm(Model model,@RequestParam(value = "filterType") String filterType, @RequestParam(value = "filterContent") String filterContent){
        ArrayList<Book> filterArrayList = new ArrayList<Book>();

        switch(filterType) {
            case "title":
                for (int i=0; i<booksArray.length; i++){
                    if(booksArray[i].getTitle() != null && booksArray[i].getTitle() != "" && booksArray[i].getTitle().equals(filterContent)) {
                        filterArrayList.add(booksArray[i]);
                    }
                }
                break;
            case "author":
                for (int i=0; i<booksArray.length; i++){
                    if(booksArray[i].getAuthor() != null && booksArray[i].getAuthor() != "" && booksArray[i].getAuthor().equals(filterContent)) {
                        filterArrayList.add(booksArray[i]);
                    }
                }
                break;
            case "year":
                for (int i=0; i<booksArray.length; i++){
                    if(booksArray[i].getYear() != null && booksArray[i].getYear() != "" && booksArray[i].getYear().equals(filterContent)) {
                        filterArrayList.add(booksArray[i]);
                    }
                }
                break;
            case "isbn":
                for (int i=0; i<booksArray.length; i++){
                    if(booksArray[i].getIsbn() != null && booksArray[i].getIsbn() != "" && booksArray[i].getIsbn().equals(filterContent)) {
                        filterArrayList.add(booksArray[i]);
                    }
                }
                break;
        }
        model.addAttribute("booksArray", filterArrayList);

        return "result";
    }

    @GetMapping("/sort")
    public String sortForm(Model model, @RequestParam(value = "sortType") String sortType){

        switch(sortType) {
            case "title":
                Arrays.sort(booksArray, Book.TitleComparator);
                break;
            case "author":
                Arrays.sort(booksArray, Book.AuthorComparator);
                break;
            case "yearasc":
                Arrays.sort(booksArray, Book.YearComparatorAsc);
                break;
            case "yeardes":
                Arrays.sort(booksArray, Book.YearComparatorDesc);
                break;
        }
        model.addAttribute("booksArray", booksArray);
        return "result";
    }

}
