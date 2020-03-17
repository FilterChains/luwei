package com.luwei.util;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadTest implements Serializable {

    private String userName;
    private int userAge;
    private byte userSex;
    private String userAddress;

    private volatile boolean flag;

}
