package org.example.controller;

import org.example.bd.Request;
import org.example.bd.User;
import org.example.service.RequestService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Request> getAllRequests(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        return requestService.getAllRequestsByUser(user);
    }

    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody Request request, Principal principal) {
        try {
            // Получение текущего пользователя
            String username = principal.getName();
            User user = userService.findByUsername(username);

            // Установка пользователя в запрос
            request.setUser(user);

            Request createdRequest = requestService.createRequest(request);
            return ResponseEntity.ok(createdRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequestStatus(@PathVariable Long id, @RequestBody Request request) {
        Request existingRequest = requestService.getRequestById(id);
        if (existingRequest != null) {
            existingRequest.setStatus(request.getStatus());
            existingRequest.setRejectionReason(request.getRejectionReason());
            requestService.updateRequest(existingRequest);
            return ResponseEntity.ok(existingRequest);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.ok().build();
    }
}
