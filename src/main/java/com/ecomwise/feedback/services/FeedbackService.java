package com.ecomwise.feedback.services;

import com.ecomwise.feedback.model.Feedback;
import com.ecomwise.feedback.model.dto.FeedBackDTO;
import com.ecomwise.feedback.model.dto.FeedBackRequestDTO;
import com.ecomwise.feedback.repository.FeedBackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedBackRepository feedbackRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public FeedBackDTO saveFeedback(FeedBackRequestDTO feedBackRequestDTO) {
        Feedback feedback = objectMapper.convertValue(feedBackRequestDTO, Feedback.class);
        feedback.setCreatedAt(LocalDateTime.now());
        return objectMapper.convertValue(feedbackRepository.save(feedback), FeedBackDTO.class);
    }

    @Transactional
    public FeedBackDTO analyzeAndSaveFeedback(Long feedbackId) throws IOException {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado"));

        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(feedback.getContent())
                    .setType(Type.PLAIN_TEXT)
                    .build();

            Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

            feedback.setSentiment(getSentimentLabel(sentiment.getScore()));
            feedback.setCategory(classifyFeedback(feedback.getContent()));
            feedback.setSuggestion(generateSuggestion(feedback.getCategory()));
            feedback.setCreatedAt(LocalDateTime.now());
        }
        return objectMapper.convertValue(feedbackRepository.save(feedback), FeedBackDTO.class);
    }

    private String getSentimentLabel(float score) {
        if (score > 0.3) return "Positivo";
        if (score < -0.3) return "Negativo";
        return "Neutro";
    }

    private String classifyFeedback(String content) {
        if (content.toLowerCase().contains("produto")) {
            return "Produto";
        } else if (content.toLowerCase().contains("entrega")) {
            return "Entrega";
        } else if (content.toLowerCase().contains("atendimento")) {
            return "Atendimento";
        } else {
            return "Geral";
        }
    }

    private String generateSuggestion(String category) {
        return switch (category) {
            case "Produto" -> "Considere melhorar a descrição e imagens dos produtos.";
            case "Entrega" -> "Avalie os prazos e a comunicação sobre o status dos pedidos.";
            case "Atendimento" -> "Treinamento contínuo da equipe pode melhorar a experiência do cliente.";
            default -> "Revise os feedbacks para encontrar oportunidades de melhoria.";
        };
    }
}
