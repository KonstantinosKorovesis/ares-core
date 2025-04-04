package com.spacex.marsops.arescore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Resource {

        /**
         * Resource's ID.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        /**
         * Resource's Name.
         */
        @NotBlank(message = "Resource Name is mandatory")
        private String name;

        /**
         * Resource's Quantity.
         */
        private Long quantity;

        /**
         * Resource's Category.
         */
        private String category;

        /**
         * Default constructor.
         */
        public Resource() {
        }

        /**
         * Core constructor.
         * 
         * @param name          Resource's name.
         * @param quantity      Resource's quantity.
         * @param category      Resource's category.
         * 
         */
        public Resource(String name, Long quantity, String category) {
                this.name = name;
                this.quantity = quantity;
                this.category = category;
        }

        /**
         * Gets Resource's ID.
         * 
         * @return The Resource's ID.
         */
        public Long getId() {
                return id;
        }

        /**
         * Sets Resource's ID.
         * 
         * @param id Passed Resource's ID.
         */
        public void setId(Long id) {
                this.id = id;
        }

        /**
         * Gets Resource's name.
         * 
         * @return The Resource's name.
         */
        public String getName() {
                return name;
        }

        /**
         * Sets Resource's name.
         * 
         * @param name Passed Resource's name.
         */
        public void setName(String name) {
                this.name = name;
        }

        /**
         * Gets Resource's quantity.
         * 
         * @return The Resource's quantity.
         */
        public Long getQuantity() {
                return quantity;
        }

        /**
         * Sets Resource's quantity.
         * 
         * @param quantity Passed Resource's quantity.
         */
        public void setQuantity(Long quantity) {
                this.quantity = quantity;
        }

        /**
         * Gets Resource's category.
         * 
         * @return The Resource's category
         */
        public String getCategory() {
                return category;
        }

        /**
         * Sets Resource's category.
         * 
         * @param category Passed Resource's category
         */
        public void setCategory(String category) {
                this.category = category;
        }
}
