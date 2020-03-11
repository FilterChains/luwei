package com.luwei.util;


import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NotThreadSafe
public class ThreadTest implements Serializable {

    private String userName;
    private int userAge;
    private byte userSex;
    private String userAddress;

}
