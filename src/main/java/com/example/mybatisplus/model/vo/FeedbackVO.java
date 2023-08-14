package com.example.mybatisplus.model.vo;

import com.example.mybatisplus.model.domain.Feedback;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FeedbackVO extends Feedback {
    private String stateName;
}
