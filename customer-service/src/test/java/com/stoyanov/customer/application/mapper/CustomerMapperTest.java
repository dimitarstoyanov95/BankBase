package com.stoyanov.customer.application.mapper;

import com.stoyanov.customer.application.dto.CustomerDTO;
import com.stoyanov.customer.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper();
    }

    @Test
    void testToDto_whenGivenCustomer_shouldReturnCustomerDTO() {
        // Given
        Customer customer = createCustomer("Viktor", "Rentea", "viktorentea@gmail.com");

        // When
        CustomerDTO dto = customerMapper.toDto(customer);

        // Then
        assertThat(dto.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(customer.getLastName());
        assertThat(dto.getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    void testToEntity_whenGivenCustomerDTO_shouldReturnCustomer() {
        // Given
        CustomerDTO customerDTO = createCustomerDTO("Viktor", "Rentea", "viktorentea@gmail.com");

        // When
        Customer customer = customerMapper.toEntity(customerDTO);

        // Then
        assertThat(customer.getFirstName()).isEqualTo(customerDTO.getFirstName());
        assertThat(customer.getLastName()).isEqualTo(customerDTO.getLastName());
        assertThat(customer.getEmail()).isEqualTo(customerDTO.getEmail());
    }

    @Test
    void testToDtoList_whenGivenListOfCustomers_shouldReturnListOfCustomerDTOs() {
        // Given
        Customer firstCustomer = createCustomer("Venkat", "Subramaniam", "subramaniam@gmail.com");
        Customer secondCustomer = createCustomer("Viktor", "Rentea", "viktorentea@gmail.com");
        List<Customer> customers = List.of(firstCustomer, secondCustomer);

        // When
        List<CustomerDTO> customerDTOS = customerMapper.toDtoList(customers);

        // Then
        assertThat(customerDTOS).hasSize(2);
        assertThat(customerDTOS.get(0).getFirstName()).isEqualTo(firstCustomer.getFirstName());
        assertThat(customerDTOS.get(1).getFirstName()).isEqualTo(secondCustomer.getFirstName());
    }

    @Test
    void testToEntityList_whenGivenListOfCustomerDTOs_shouldReturnListOfCustomers() {
        // Given
        CustomerDTO firstCustomerDto = createCustomerDTO("Venkat", "Subramaniam", "subramaniam@gmail.com");
        CustomerDTO secondCustomerDto = createCustomerDTO("Viktor", "Rentea", "viktorentea@gmail.com");
        List<CustomerDTO> dtos = List.of(firstCustomerDto, secondCustomerDto);

        // When
        List<Customer> customers = customerMapper.toEntityList(dtos);

        // Then
        assertThat(customers).hasSize(2);
        assertThat(customers.get(0).getFirstName()).isEqualTo(firstCustomerDto.getFirstName());
        assertThat(customers.get(1).getFirstName()).isEqualTo(secondCustomerDto.getFirstName());
    }

    private Customer createCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        return customer;
    }

    private CustomerDTO createCustomerDTO(String firstName, String lastName, String email) {
        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        return dto;
    }
}
