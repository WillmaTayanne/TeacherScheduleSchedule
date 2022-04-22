package com.pdist.schedule.Model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @NotNull
    private String registration;

    @NotNull
    private String password;
}
