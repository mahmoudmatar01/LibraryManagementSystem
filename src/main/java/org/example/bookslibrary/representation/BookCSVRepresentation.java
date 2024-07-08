package org.example.bookslibrary.representation;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookCSVRepresentation {
    @CsvBindByName(column = "book_title")    // book_title representation for first column in CSV file
    private String title;
    @CsvBindByName(column = "book_author")   // book_author representation for second column in CSV file
    private String author;
    @CsvBindByName(column = "book_isbn")   // book_isbn representation for third column in CSV file
    private String isbn;

}
