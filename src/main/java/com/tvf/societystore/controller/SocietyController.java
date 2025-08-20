package com.tvf.societystore.controller;

import com.tvf.societystore.dto.user.UserSummaryDTO;
import com.tvf.societystore.service.SocietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/societies")
@RequiredArgsConstructor
public class SocietyController {

    private final SocietyService societyService;

    @PostMapping("/{societyId}/join")
    @PreAuthorize("hasAuthority('RESIDENT')")
    public ResponseEntity<Void> requestToJoin(@PathVariable Long societyId) {
        societyService.requestToJoinSociety(societyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/pending-requests")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserSummaryDTO>> getPendingRequests() {
        return ResponseEntity.ok(societyService.getPendingJoinRequests());
    }

    @PutMapping("/admin/approve-user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> approveUser(@PathVariable Long userId) {
        societyService.approveJoinRequest(userId);
        return ResponseEntity.ok().build();
    }
}
