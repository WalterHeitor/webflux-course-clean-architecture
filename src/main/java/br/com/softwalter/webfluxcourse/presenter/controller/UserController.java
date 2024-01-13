package br.com.softwalter.webfluxcourse.presenter.controller;

import br.com.softwalter.webfluxcourse.presenter.controller.exceptions.StandardError;
import br.com.softwalter.webfluxcourse.presenter.controller.model.request.UserRequest;
import br.com.softwalter.webfluxcourse.presenter.controller.model.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserController {

    @Operation(
            summary = "Save a new user",
            tags = { "MyUser" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "User saved successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid user data",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
     @PostMapping
    ResponseEntity<Mono<Void>> save(@Valid @RequestBody UserRequest request);

    @Operation(
            summary = "Find purchase order by ID",
            tags = { "MyUser" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(schema = @Schema(implementation = StandardError.class))),
                    @ApiResponse(responseCode = "400", description = "User bad request",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @GetMapping(value = "/{id}")
    ResponseEntity<Mono<UserResponse>> findById(@PathVariable String id);

    @Operation(
            summary = "Find all users",
            tags = { "MyUser" },
            responses = @ApiResponse(responseCode = "200", description = "Users found successfully")
    )
    @GetMapping
    ResponseEntity<Flux<UserResponse>> findAll();

    @Operation(
            summary = "Update user by ID",
            tags = { "MyUser" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PatchMapping(value = "/{id}")
    ResponseEntity<Mono<UserResponse>> update(@PathVariable String id, @RequestBody UserRequest request);

    @Operation(
            summary = "Delete user by ID",
            tags = { "MyUser" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "User deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Mono<Void>> delete(@PathVariable String id);
}
