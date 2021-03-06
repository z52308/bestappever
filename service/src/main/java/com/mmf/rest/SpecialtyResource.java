package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.SpecialtyService;
import com.mmf.business.domain.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * svetlana.voyteh
 * 06.03.13
 */
@Service
@Path("specialty")
public class SpecialtyResource {

    @Autowired
    private SpecialtyService specialtyService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialities(){
        try {
            List<Specialty> specialtyList = specialtyService.list();
            return Response.ok(specialtyList).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
