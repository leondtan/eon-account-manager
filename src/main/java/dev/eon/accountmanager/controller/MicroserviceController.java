package dev.eon.accountmanager.controller;

import dev.eon.accountmanager.adapter.rabbitmq.RabbitMqAdapter;
import dev.eon.accountmanager.model.request.CreatePromotionRequest;
import dev.eon.accountmanager.model.response.ErrorResponse;
import dev.eon.accountmanager.model.response.SuccessResponse;
import dev.eon.accountmanager.util.ValueUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("${app.base-path}/service")
public class MicroserviceController {

    private RabbitMqAdapter rabbitMq;

    public MicroserviceController(RabbitMqAdapter rabbitMqAdapter) {
        this.rabbitMq = rabbitMqAdapter;
    }

    @PostMapping("promotion")
    public ResponseEntity<?> promote(@RequestBody() CreatePromotionRequest request) {
        try {
            rabbitMq.pushMessage("promotional", ValueUtil.gson.toJson(request));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                    "Server Busy, please wait",
                    HttpStatus.BAD_REQUEST
            ));
        }

        return ResponseEntity.ok(new SuccessResponse(
                request,
                HttpStatus.OK
        ));
    }
}
