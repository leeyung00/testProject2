package com.example.servertestproject.local.Tip;

import com.example.servertestproject.local.Tip.entity.Tip;
import com.example.servertestproject.local.Tip.entity.TipRequest;
import com.example.servertestproject.local.Tip.entity.TipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipService {
    private final TipRepository tipRepository;

    public Long save(TipRequest tipRequest) {
        Tip tip = Tip.builder()
                .tip(tipRequest.tip())
                .build();
        tipRepository.save(tip);

        return TipResponse.from(tip).id();
    }

    public Page<TipResponse> findAll(Pageable pageable) {
        return tipRepository.findAll(pageable)
                .map(TipResponse::from);
    }

    public Long deleteById(Long id) {
        tipRepository.deleteById(id);
        return id;
    }

    public Long update(Long id, TipRequest tipRequest) {
        Tip tip = tipRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 팁은 존재하지 않습니다"));
        tip.update(tipRequest.tip());
        return id;
    }
}
