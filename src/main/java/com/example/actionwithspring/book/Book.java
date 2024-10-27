package com.example.actionwithspring.book;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book {
    String id;
    String name;
    String colour;
}
