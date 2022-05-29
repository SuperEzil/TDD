package com.example.tdd.controller;


import com.example.tdd.controller.exception.*;
import com.example.tdd.controller.response.AccountResponse;
import com.example.tdd.controller.response.BaseResponse;
import com.example.tdd.data.UserInfo;
import com.example.tdd.data.entity.Account;
import com.example.tdd.service.AccoutService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Accoount")
@RequiredArgsConstructor
public class AccountController {

    private AccoutService accoutService;

    @Autowired
    public AccountController(AccoutService accoutService) {
        this.accoutService = accoutService;
    }


    @PostMapping("/")
    @Operation(summary = "Create user", description = "Create user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "가입 결과", response =Account.class)
            , @ApiResponse(code = 409, message = "중복 가입 오류 ", response = BaseResponse.class)
    })
    public ResponseEntity createUser(@Parameter(description = "User name") @RequestParam("name") String name
            , @Parameter(description = "User password") @RequestParam("password") String password) throws ConflictException {
        return ResponseEntity.ok(accoutService.create(name, password));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 결과", response =Account.class)
            , @ApiResponse(code = 404, message = "찾을수 없음", response = BaseResponse.class)})
    public ResponseEntity getUser(@Parameter(description = "User id", required = true) @PathVariable("id") Integer id) throws NotFoundException {
        return ResponseEntity.ok(accoutService.getAccount(id));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Modify user", description = "Modify user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "변경 결과", response =Account.class)
            , @ApiResponse(code = 304, message = "변경 실패", response = BaseResponse.class)
            , @ApiResponse(code = 404, message = "찾을수 없음", response = BaseResponse.class)})
    public ResponseEntity modifyUser(@Parameter(description = "User id") @PathVariable("id") Integer id
            , @Valid @Parameter(description = "User info") @RequestBody UserInfo info) throws NotFoundException, NotModifiedException {
        return ResponseEntity.ok(accoutService.updateAccount(id, info));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "삭제 결과", response =Account.class)
            , @ApiResponse(code = 404, message = "찾을수 없음", response = BaseResponse.class)})
    public ResponseEntity<?> deleteUser(@Parameter(description = "User id") @PathVariable("id") Integer id) throws NotFoundException {
        return ResponseEntity.ok(accoutService.deleteAccount(id));
    }

}

