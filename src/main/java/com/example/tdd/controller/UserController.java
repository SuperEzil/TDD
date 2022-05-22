package com.example.tdd.controller;


import com.example.tdd.data.UserInfo;
import com.example.tdd.data.UserResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/user")
@Api(tags = "User")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/{id}")
   // @ApiOperation(value = "Get user by id", notes = "Get user by id")
    @Operation(summary = "Get user by id", description = "Get user by id")
    public String getUser(@Parameter(description = "User id") @PathVariable("id") String id) {
        return "";
    }

    @PostMapping("/")
    //@ApiOperation(value = "Create user", notes = "Create user")
    @Operation(summary = "Create user", description = "Create user")
    public String createUser(@Parameter(description = "User id") @RequestParam("id") String id,
                               @Parameter(description = "User name") @RequestParam("name") String name) {
        return "'new UserInfo(id, name)'";
    }

    @PutMapping("/")
    //@ApiOperation(value = "Modify user", notes = "Modify user")
    @Operation(summary = "Modify user", description = "Modify user")
    public UserResponse modifyUser(@Parameter(description = "User info")@RequestBody UserInfo info){

        return null;
    }

    @DeleteMapping("/{id}")
    //@ApiOperation(value = "Delete user", notes = "Delete user")
    @Operation(summary = "Delete user", description = "Delete user")
    public String deleteUser(@PathVariable String id){

        return "";
    }

}

