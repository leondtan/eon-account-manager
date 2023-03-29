package dev.eon.accountmanager.controller;

import dev.eon.accountmanager.entity.BalanceTransaction;
import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.model.repository.BalanceTransactionSpecificSummary;
import dev.eon.accountmanager.model.repository.BalanceTransactionSummary;
import dev.eon.accountmanager.model.repository.BalanceTransactionUserSummary;
import dev.eon.accountmanager.model.request.CreateBalanceTransactionRequest;
import dev.eon.accountmanager.model.response.SuccessResponse;
import dev.eon.accountmanager.service.BalanceTransactionService;
import dev.eon.accountmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.base-path}/balance")
public class BalanceTransactionController {

    private final BalanceTransactionService _service;
    private final UserService _userService;

    public BalanceTransactionController(
            BalanceTransactionService balanceTransactionService,
            UserService userService
    ) {
        this._service = balanceTransactionService;
        this._userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody CreateBalanceTransactionRequest request) {
        request.setUserId(null);
        BalanceTransaction newTransaction = _service.save(request);

        return ResponseEntity.ok(new SuccessResponse(
                newTransaction,
                HttpStatus.CREATED
        ));
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody CreateBalanceTransactionRequest request) {
        BalanceTransaction newTransaction = _service.save(request);

        return ResponseEntity.ok(new SuccessResponse(
                newTransaction,
                HttpStatus.CREATED
        ));
    }

    @GetMapping
    public ResponseEntity<?> getByUser(@RequestParam(required = false) String type) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = _userService.getByEmail(currentEmail);

        List<BalanceTransaction> result = _service.getByUser(currentUser, type);

        return ResponseEntity.ok(new SuccessResponse(
                result,
                HttpStatus.OK
        ));
    }

    @GetMapping("summary")
    public ResponseEntity<?> getSummaryByUser(@RequestParam(required = false) String type) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = _userService.getByEmail(currentEmail);

        if(type == null || type.isEmpty()) {
            List<BalanceTransactionSummary> result = _service.getSummaryByUser(currentUser);

            return ResponseEntity.ok(new SuccessResponse(
                    result,
                    HttpStatus.OK
            ));
        } else {
            List<BalanceTransactionSpecificSummary> result = _service.getSpecifiedSummaryByUser(currentUser, type);

            return ResponseEntity.ok(new SuccessResponse(
                    result,
                    HttpStatus.OK
            ));
        }
    }

    @GetMapping("summary/user")
    public ResponseEntity<?> getGeneralSummary(
            @RequestParam() String type,
            @RequestParam() String period
    ) {
        List<BalanceTransactionUserSummary> result = _service.getSpecifiedSummary(type, period);

        return ResponseEntity.ok(new SuccessResponse(
                result,
                HttpStatus.OK
        ));
    }

}
