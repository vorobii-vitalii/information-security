package org.example.solution.service;


import org.example.solution.Response;

public interface UserAuthorizationService {
    Response tryToGetAccess(String userName, Integer input, Integer entered);
}
