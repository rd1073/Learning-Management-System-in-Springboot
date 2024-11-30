package com.example.lms.service;

import com.example.lms.dto.MockDetailsDTO;
import com.example.lms.entity.MockDetails;

public interface MockDetailsServiceInterface {

     
    MockDetails createMock(MockDetailsDTO mockDetailsDTO) throws Exception;
}
