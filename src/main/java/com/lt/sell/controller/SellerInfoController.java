package com.lt.sell.controller;

import com.lt.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SellerInfoController {
    @Autowired
    SellerService sellerService;

}
