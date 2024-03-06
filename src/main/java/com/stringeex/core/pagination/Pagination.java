package com.stringeex.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    Long pageNo = 1L;
    Integer pageSize = 10;
    Long totalItems = 0L;
    Long totalPages = 1L;

    public Long getTotalPages() {
        if (totalItems > 0 && pageSize > 0) {
            Long total = totalItems / pageSize;
            return totalItems % pageSize > 0 ? total + 1 : total;
        }

        return totalPages;
    }
}
