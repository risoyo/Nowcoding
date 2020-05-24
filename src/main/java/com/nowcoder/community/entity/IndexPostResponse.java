package com.nowcoder.community.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class IndexPostResponse {
    @NotBlank
    private int id;

    @NotBlank
    private int user_id;

    @NotBlank
    private String user_name;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private int type;

    @NotBlank
    private int status;

    @NotBlank
    private Date create_time;

    @NotBlank
    private int comment_count;

    @NotBlank
    private double score;

}
