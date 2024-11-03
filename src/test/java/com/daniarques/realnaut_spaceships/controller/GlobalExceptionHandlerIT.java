package com.daniarques.realnaut_spaceships.controller;

import com.daniarques.realnaut_spaceships.domain.ShowServiceImpl;
import com.daniarques.realnaut_spaceships.domain.exception.InvalidParameterException;
import com.daniarques.realnaut_spaceships.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShowController.class)
class GlobalExceptionHandlerIT {

    private static final long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowServiceImpl showService;

    @Test
    void when_apiCallThrowsInvalidParameterException_should_return404() throws Exception {

        given(this.showService.getShowById(ID)).willThrow(new InvalidParameterException("msg"));

        this.mockMvc.perform(get("/shows/1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("msg"))
                .andExpect(jsonPath("$.path").value("/shows/1"));
    }

    @Test
    void when_apiCallThrowsNotFoundException_should_return404() throws Exception {

        given(this.showService.getShowById(ID)).willThrow(new NotFoundException("msg"));

        this.mockMvc.perform(get("/shows/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("msg"))
                .andExpect(jsonPath("$.path").value("/shows/1"));
    }

}