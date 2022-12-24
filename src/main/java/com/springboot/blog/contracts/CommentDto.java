package com.springboot.blog.contracts;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private long id;
    @NotEmpty

    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String body;
}
