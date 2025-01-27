package com.management.clinic.service;

import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.entity.MedicalResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MedicalResultService {

    MedicalResult save(MedicalResult medicalResult);

    MedicalResult update(MedicalResult medicalResult);

    MedicalResult findById(Long id);

    boolean delete(Long id);

    MedicalResult buildData(HttpServletRequest req);

    MedicalResult buildMedicalResultAdd(HttpServletRequest req);

    List<MedicalMethod> buildListMedicalMethodAdd(HttpServletRequest req);
}
