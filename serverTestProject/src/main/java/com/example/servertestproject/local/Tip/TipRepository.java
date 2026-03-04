package com.example.servertestproject.local.Tip;

import com.example.servertestproject.local.Tip.entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipRepository extends JpaRepository<Tip, Long> {
}
