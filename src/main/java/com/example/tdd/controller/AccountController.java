package com.example.tdd.controller;


import com.example.tdd.controller.enums.enumValue;
import com.example.tdd.controller.response.BaseResponse;
import com.example.tdd.data.UserInfo;
import com.example.tdd.data.entity.Account;
import com.example.tdd.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name ="AccountController", description = "사용자 가입 및 조회등의 기능을 제공하는 컨틀롤러")
@RequiredArgsConstructor
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/{name}")
    @Operation(summary = "Create user", description = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가입 결과", content = @Content(schema = @Schema(implementation = Account.class) ) )
            , @ApiResponse(responseCode = "409", description = "중복 가입 오류 ", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public ResponseEntity<Account> createUser(@Parameter(description = "User name") @PathVariable("name") String name
            , @Parameter(description = "User password") @RequestParam("password") String password) {
        return ResponseEntity.ok(accountService.create(name, password));
    }

    //@GetMapping("/{name}")
    @GetMapping("/{name}/{type}")
    @Operation(summary = "Get user by Account Info", description = "Get user by Account Info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 결과")
            , @ApiResponse(responseCode = "404", description = "찾을수 없음", content =@Content(schema = @Schema(implementation = BaseResponse.class)))})
    public ResponseEntity<Account> getUser(@Parameter(description = "User name", required = true) @PathVariable("name") String name
            ,@Parameter(description = "스웨거 열거형 파리미터", schema = @Schema(implementation = enumValue.class)) @PathVariable("type") enumValue enumValue)  {
        return ResponseEntity.ok(accountService.getAccount(name));

    }

    @PutMapping("/{name}")
    @Operation(summary = "Modify user", description = "Modify user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "변경 결과", content =@Content(schema = @Schema(implementation = Account.class)))
            , @ApiResponse(responseCode = "304", description = "변경 실패", content =@Content(schema = @Schema(implementation = BaseResponse.class)))
            , @ApiResponse(responseCode = "404", description = "찾을수 없음", content =@Content(schema = @Schema(implementation = BaseResponse.class)))})
    public ResponseEntity<Account> modifyUser(@Parameter(description = "User name") @PathVariable("name") Integer name
            , @Valid @Parameter(description = "User info") @RequestBody UserInfo info) {
        return ResponseEntity.ok(accountService.updateAccount(name, info));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 결과", content =@Content(schema = @Schema(implementation = Account.class)))
            , @ApiResponse(responseCode = "404", description = "찾을수 없음", content =@Content(schema = @Schema(implementation = BaseResponse.class)))})
    public ResponseEntity<Account> deleteUser(@Parameter(description = "User id") @PathVariable("id") Integer id) {
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

}

