package com.example.servertestproject.local.Tip.entity;

import jakarta.validation.constraints.NotBlank;

public record TipRequest(
        @NotBlank String tip
) {}
