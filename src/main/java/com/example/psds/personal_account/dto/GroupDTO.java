package com.example.psds.personal_account.dto;

import com.example.psds.personal_account.model.RoleInGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupDTO {
    private long id;
    private String name;
    private String description;
}
