package com.example.servertestproject.local.Tip.entity;

public record TipResponse(
        Long id,
        String tip
) {
    public static TipResponse from(Tip tip) {
        return new TipResponse(
                tip.getId(),
                tip.getTip()
        );
    }
}
