package com.example.parrot.common;

import lombok.Data;

@Data
public class PageRequest {
    private int current = 1;
    private int size = 10;

}
