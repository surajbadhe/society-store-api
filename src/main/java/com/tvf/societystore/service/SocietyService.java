package com.tvf.societystore.service;

import com.tvf.societystore.dto.user.UserSummaryDTO;
import java.util.List;

public interface SocietyService {
    void requestToJoinSociety(Long societyId);
    List<UserSummaryDTO> getPendingJoinRequests();
    void approveJoinRequest(Long userId);
}

