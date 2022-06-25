package com.jm.cloud.entity.Request;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserItem {

    @NonNull
    private String name;
    @NonNull
    private String phone;
    private String address;
    @NonNull
    private String userType;
}
