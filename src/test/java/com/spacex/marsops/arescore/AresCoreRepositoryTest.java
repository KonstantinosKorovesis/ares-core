package com.spacex.marsops.arescore;

import org.junit.jupiter.api.Test;
import com.spacex.marsops.arescore.model.Resource;
import com.spacex.marsops.arescore.repository.AresCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AresCoreRepositoryTest {

    @Autowired
    private AresCoreRepository aresCoreRepository;

    @Test
    public void testSaveResource() {
        Resource resource = new Resource("Oxygen Tank", 10L, "Life Support");

        Resource savedResource = aresCoreRepository.save(resource);

        assertThat(savedResource).isNotNull();
        assertThat(savedResource.getId()).isNotNull();
        assertThat(savedResource.getName()).isEqualTo("Oxygen Tank");
        assertThat(savedResource.getQuantity()).isEqualTo(10L);
        assertThat(savedResource.getCategory()).isEqualTo("Life Support");
    }

    @Test
    public void testFindById() {
        Resource resource = new Resource("Oxygen Tank", 10L, "Life Support");
        Resource savedResource = aresCoreRepository.save(resource);

        Resource foundResource = aresCoreRepository.findById(savedResource.getId()).orElse(null);

        assertThat(foundResource).isNotNull();
        assertThat(foundResource.getId()).isEqualTo(savedResource.getId());
        assertThat(foundResource.getName()).isEqualTo("Oxygen Tank");
    }

    @Test
    public void testFindAll() {
        Resource resource1 = new Resource("Oxygen Tank", 10L, "Life Support");
        Resource resource2 = new Resource("Food Rations", 5L, "Supplies");
        aresCoreRepository.save(resource1);
        aresCoreRepository.save(resource2);

        Iterable<Resource> resources = aresCoreRepository.findAll();

        assertThat(resources).hasSize(2);
    }

    @Test
    public void testDeleteById() {
        Resource resource = new Resource("Oxygen Tank", 10L, "Life Support");
        Resource savedResource = aresCoreRepository.save(resource);

        aresCoreRepository.deleteById(savedResource.getId());
        Resource deletedResource = aresCoreRepository.findById(savedResource.getId()).orElse(null);

        assertThat(deletedResource).isNull();
    }

    @Test
    public void testDeleteByIdWithException() {
        try {
            aresCoreRepository.deleteById(999L); // Non-existent ID
        } catch (EmptyResultDataAccessException e) {
            assertThat(e).isInstanceOf(EmptyResultDataAccessException.class);
        }
    }
}