package com.stringeex.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SamEntity {
    Long id;
    String name;
    String description;
    Date createdAt;
    Date updatedAt;
}
