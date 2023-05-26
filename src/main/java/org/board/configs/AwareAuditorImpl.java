package org.board.configs;

import lombok.RequiredArgsConstructor;
import org.board.commons.MemberUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class AwareAuditorImpl implements AuditorAware<String> {

    private final MemberUtil memberUtil;
    @Override
    public Optional<String> getCurrentAuditor() {

        String userId = memberUtil.isLogin() ? memberUtil.getMember().getUserId() : null;
        return Optional.ofNullable(userId);
    }
}
