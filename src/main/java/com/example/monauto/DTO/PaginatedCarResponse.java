package com.example.monauto.DTO;

import com.example.monauto.entity.Auto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor
@AllArgsConstructor
public class PaginatedCarResponse {
    private List<Auto> content;
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;
    private boolean lastPage;
}
