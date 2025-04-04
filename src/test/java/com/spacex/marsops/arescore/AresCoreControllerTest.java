package com.spacex.marsops.arescore;

import com.spacex.marsops.arescore.model.Resource;
import com.spacex.marsops.arescore.controller.AresCoreController;
import com.spacex.marsops.arescore.repository.AresCoreRepository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AresCoreControllerTest {

    @Mock
    private AresCoreRepository aresCoreRepository;

    @InjectMocks
    private AresCoreController aresCoreController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(aresCoreController).build();
    }

    @Test
    public void testShowResources() throws Exception {
        Resource resource = new Resource("Oxygen Tank", 10L, "Life Support");
        resource.setId(1L);

        when(aresCoreRepository.findAll()).thenReturn(List.of(resource));

        mockMvc.perform(get("/resources"))
                .andExpect(status().isOk())
                .andExpect(view().name("resource-list"))
                .andExpect(model().attributeExists("resources"));
    }

    @Test
    public void testShowAddForm() throws Exception {
        mockMvc.perform(get("/resources/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-resource"))
                .andExpect(model().attributeExists("resource"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void testAddResource() throws Exception {
        when(aresCoreRepository.save(any(Resource.class))).thenReturn(new Resource("Oxygen Tank", 10L, "Life Support"));

        Resource resource = new Resource("Oxygen Tank", 10L, "Life Support");
        resource.setId(1L);
        mockMvc.perform(post("/resources/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", resource.getName())
                        .param("category", resource.getCategory())
                        .param("quantity", String.valueOf(resource.getQuantity())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/resources"));

        verify(aresCoreRepository, times(1)).save(any(Resource.class));
    }

    @Test
    public void testShowEditForm() throws Exception {
        Resource resource = new Resource("Oxygen Tank", 10L, "Life Support");
        resource.setId(1L);

        when(aresCoreRepository.findById(1L)).thenReturn(Optional.of(resource));

        mockMvc.perform(get("/resources/edit/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-resource"))
                .andExpect(model().attributeExists("resource"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void testEditResource() throws Exception {
        Resource resource = new Resource("Oxygen Tank", 10L, "Life Support");
        resource.setId(1L);

        mockMvc.perform(post("/resources/edit/{id}", 1L)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Oxygen Tank 2.0")
                        .param("category", "Life Support")
                        .param("quantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/resources"));

        verify(aresCoreRepository, times(1)).save(any(Resource.class));
    }

    @Test
    public void testDeleteResource() throws Exception {
        Resource resource = new Resource("Oxygen Tank", 10L, "Life Support");
        resource.setId(1L);
        
        mockMvc.perform(get("/resources/delete/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/resources"));

        verify(aresCoreRepository, times(1)).deleteById(1L);
    }
}