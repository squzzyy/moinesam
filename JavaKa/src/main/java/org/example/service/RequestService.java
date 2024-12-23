package org.example.service;

import org.example.bd.Request;
import org.example.bd.User;
import org.example.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request> getAllRequestsByUser(User user) {
        return requestRepository.findByUser(user);
    }

    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    public Request updateRequest(Request request) {
        return requestRepository.save(request);
    }

    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public Request getRequestById(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        return request.orElse(null);
    }
}
