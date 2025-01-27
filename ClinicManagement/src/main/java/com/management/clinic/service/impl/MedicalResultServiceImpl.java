package com.management.clinic.service.impl;

import com.management.clinic.dao.MedicalResultDAO;
import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.entity.MedicalResult;
import com.management.clinic.service.MedicalResultService;
import com.management.clinic.service.MedicalScheduleService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class MedicalResultServiceImpl implements MedicalResultService {

    @Inject
    private MedicalResultDAO medicalResultDAO;

    @Inject
    private MedicalScheduleService scheduleService;

    @Override
    public MedicalResult save(MedicalResult medicalResult) {
        Long id = medicalResultDAO.save(medicalResult);
        if (id != null) {
            scheduleService.updateStatus(medicalResult.getScheduleId(), Boolean.FALSE);
            return this.findById(id);
        }
        return null;
    }

    @Override
    public MedicalResult update(MedicalResult medicalResult) {
        return medicalResultDAO.update(medicalResult);
    }

    @Override
    public MedicalResult findById(Long id) {
        return medicalResultDAO.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        try {
            return medicalResultDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MedicalResult buildData(HttpServletRequest req) {
        if (req != null) {
            return MedicalResult.builder()
                    .id(Long.parseLong(req.getParameter("id")))
                    .scheduleId(Long.parseLong(req.getParameter("scheduleId")))
                    .doctorId(Long.parseLong(req.getParameter("doctorId")))
                    .name(req.getParameter("name"))
                    .diagnosis(req.getParameter("diagnosis"))
                    .conclude(req.getParameter("conclude"))
                    .build();
        }
        return null;
    }

    public MedicalResult buildMedicalResultAdd(HttpServletRequest req) {
        if (req != null) {
            return MedicalResult.builder()
                    .scheduleId(Long.parseLong(req.getParameter("scheduleId")))
                    .patientId(Long.parseLong(req.getParameter("createdId")))
                    .name(req.getParameter("name"))
                    .diagnosis(req.getParameterValues("diagnosis")[0])
                    .conclude(req.getParameterValues("conclude")[0])
                    .build();
        }
        return null;
    }

    public List<MedicalMethod> buildListMedicalMethodAdd(HttpServletRequest req) {
        if (req != null) {
            if (req.getParameterValues("diagnosis") != null && req.getParameterValues("diagnosis").length > 1) {
                List<MedicalMethod> medicalMethods = new ArrayList<>();
                for (int i = 1; i < req.getParameterValues("diagnosis").length; i++) {
                    MedicalMethod medicalResult = MedicalMethod.builder()
                            .diagnosis(req.getParameterValues("diagnosis")[i])
                            .conclude(req.getParameterValues("conclude")[i])
                            .name(req.getParameterValues("type")[i-1])
                            .type(req.getParameterValues("type")[i-1])
                            .build();
                    medicalMethods.add(medicalResult);
                }
                return medicalMethods;
            }
        }
        return null;
    }
}
