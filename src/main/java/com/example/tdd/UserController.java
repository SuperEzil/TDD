package com.example.tdd;


import com.example.tdd.data.UserInfo;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 정보 관리 컨트롤러
 */
@RestController("user")
public class UserController {


    /**
     * 사용자 등록
     * @param info 사용자 상세 정보
     * @return
     */
    @PostMapping("/")
    public String create(@RequestBody UserInfo info){

        return "";
    }

    /**
     * 사용자 정보 조회
     * @param name
     * @return
     */
    @GetMapping("/{name}")
    public String find(@PathVariable String name){

        return "";
    }


    /**
     * 사용자 정보 수정
     * @param info
     * @return
     */
    @PutMapping("/")
    public String update(@RequestBody UserInfo info){

        return "";
    }


    /**
     * 사용자 정보 삭제
     * @param name
     * @return
     */
    @DeleteMapping("/{name}")
    public String delete(@PathVariable String name){

        return "";
    }

}
