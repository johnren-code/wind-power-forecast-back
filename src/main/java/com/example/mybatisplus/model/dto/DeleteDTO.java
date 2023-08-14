package com.example.mybatisplus.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)

public class DeleteDTO {
    private Serializable id;
    private List<Serializable> ids;
}
