package com.kyobo.koreait.controller;

import com.kyobo.koreait.domain.dtos.CartDTO;
import com.kyobo.koreait.domain.dtos.HeartDTO;
import com.kyobo.koreait.domain.dtos.UserDTO;
import com.kyobo.koreait.domain.vos.BookVO;
import com.kyobo.koreait.service.MainService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@Log4j2
@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @PermitAll
    @GetMapping("/")
    public String main(){
        log.info(" ====== main페이지 ========");
        return "/main";
    }



    @ResponseBody
    @GetMapping("/main/books")
    public List<BookVO> get_all_books(){
        return mainService.get_all_books();
    }

    @ResponseBody
    @PostMapping("/main/cart")
    public boolean insert_cart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<CartDTO> cartDTOS
    ){
        log.info(" ==== insert_cart ==== ");
        return mainService.insert_books_in_cart(userDetails, cartDTOS);
    }

    @ResponseBody
    @PostMapping("/main/heart")
    public boolean insert_heart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<HeartDTO> heartDTOS
    ){
        log.info(" ===== insert_heart ===== ");
        return mainService.insert_books_in_heart(userDetails, heartDTOS);
    }


}








