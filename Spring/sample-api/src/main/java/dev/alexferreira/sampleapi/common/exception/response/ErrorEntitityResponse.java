package dev.alexferreira.sampleapi.common.exception.response;

import java.util.Map;

public class ErrorEntitityResponse {
   public String code = "GENERIC_ERROR";
   public String message = "Erro interno";
   public Map<String, String> details;
}
