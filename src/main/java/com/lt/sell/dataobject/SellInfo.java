package com.lt.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SellInfo {
    @Id
    private String sellerId;
    private String username;
    private String password;
    private String openid;

}
