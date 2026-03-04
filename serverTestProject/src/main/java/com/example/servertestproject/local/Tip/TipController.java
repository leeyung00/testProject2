package com.example.servertestproject.local.Tip;

import com.example.servertestproject.local.Tip.entity.TipRequest;
import com.example.servertestproject.local.Tip.entity.TipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tips") // 공통 경로 추출
@CrossOrigin(origins = "http://localhost:5173")
public class TipController {
    private final TipService tipService;

    // 1. 전체 목록 조회 (페이징)
    @GetMapping
    public ResponseEntity<Page<TipResponse>> findAll(Pageable pageable) {
        Page<TipResponse> responses = tipService.findAll(pageable);
        return ResponseEntity.ok(responses);
    }

    // 2. 단건 등록 (201 Created 응답)
    @PostMapping
    public ResponseEntity<Long> save(@RequestBody TipRequest tipRequest) {
        Long id = tipService.save(tipRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    // 3. 단건 수정
    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @RequestBody TipRequest tipRequest) {
        Long updatedId = tipService.update(id, tipRequest);
        return ResponseEntity.ok(updatedId);
    }

    // 4. 단건 삭제 (204 No Content 응답 선호)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}