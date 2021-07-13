package com.inen.inenapp.service;

import com.inen.inenapp.dto.attention.Sample;
import org.springframework.web.bind.annotation.RequestBody;

public interface LabService {
    void addSample(Sample sample);
}
