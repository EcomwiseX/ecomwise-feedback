package com.ecomwise.feedback.controllers;

import com.ecomwise.feedback.model.dto.FeedBackDTO;
import com.ecomwise.feedback.model.dto.FeedBackRequestDTO;
import com.ecomwise.feedback.services.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FeedBackController {

    private final FeedbackService feedbackService;

    @Operation(summary = "Save a feedback", description = "Save a feedback")
    @PostMapping("/")
    public ResponseEntity<FeedBackDTO> saveFeedback(@RequestBody @Valid FeedBackRequestDTO feedBackRequestDTO) {
        return new ResponseEntity<>(feedbackService.saveFeedback(feedBackRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Analyze a feedback", description = "Analyze a feedback")
    @PutMapping("/{id}")
    public ResponseEntity<FeedBackDTO> analyzeFeedback(@PathParam("id") Long id) throws IOException {
        return new ResponseEntity<>(feedbackService.analyzeAndSaveFeedback(id), HttpStatus.OK);
    }
}
