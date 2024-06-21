package com.stoyanov.customer.service;

import com.stoyanov.customer.application.dto.CustomerDTO;
import com.stoyanov.customer.application.mapper.CustomerMapper;
import com.stoyanov.customer.application.repository.CustomerRepository;
import com.stoyanov.customer.domain.exception.CustomerDuplicationException;
import com.stoyanov.customer.domain.exception.CustomerNotFoundException;
import com.stoyanov.customer.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerMapper = mock(CustomerMapper.class);
        customerService = new CustomerService(customerRepository, customerMapper);
    }

    @Test
    void testGet_whenCustomerExists_shouldReturnCustomerDTO() {
        // Given
        UUID id = UUID.randomUUID();
        Customer customer = createCustomer("Uncle", "Bob", "unclebob@example.com");
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(createCustomerDTO("Uncle", "Bob", "unclebob@example.com"));

        // When
        CustomerDTO result = customerService.get(id);

        // Then
        assertThat(result.getFirstName()).isEqualTo(customer.getFirstName());
        verify(customerRepository).findById(id);
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testGet_whenCustomerDoesNotExist_shouldThrowException() {
        // Given
        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.get(id));
        verify(customerRepository).findById(id);
    }

    @Test
    void testGetAll_shouldReturnListOfCustomerDTOs() {
        // Given
        Customer customer = createCustomer("Mark", "Richards", "markrichards@example.com");
        List<Customer> customers = List.of(customer);
        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.toDtoList(customers)).thenReturn(List.of(createCustomerDTO("Mark", "Richards", "markrichards@example.com")));

        // When
        List<CustomerDTO> result = customerService.getAll();

        // Then
        assertThat(result).hasSize(1);
        verify(customerRepository).findAll();
        verify(customerMapper).toDtoList(customers);
    }

    @Test
    void testCreate_whenEmailDoesNotExist_shouldCreateCustomer() {
        // Given
        CustomerDTO customerDTO = createCustomerDTO("Viktor", "Rentea", "viktorentea@example.com");
        Customer customer = createCustomer("Viktor", "Rentea", "viktorentea@example.com");
        when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(null);
        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);
        when(customerRepository.save(customer)).thenReturn(customer);

        // When
        CustomerDTO result = customerService.create(customerDTO);

        // Then
        assertThat(result.getEmail()).isEqualTo(customerDTO.getEmail());
        verify(customerRepository).findByEmail(customerDTO.getEmail());
        verify(customerMapper).toEntity(customerDTO);
        verify(customerRepository).save(customer);
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testCreate_whenEmailExists_shouldThrowException() {
        // Given
        CustomerDTO customerDTO = createCustomerDTO("Uncle", "Bob", "unclebob@example.com");
        Customer existingCustomer = createCustomer("Uncle", "Bob", "unclebob@example.com");
        when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(existingCustomer);

        // When / Then
        assertThrows(CustomerDuplicationException.class, () -> customerService.create(customerDTO));
        verify(customerRepository).findByEmail(customerDTO.getEmail());
    }

    @Test
    void testUpdate_whenCustomerExists_shouldUpdateCustomer() {
        // Given
        UUID id = UUID.randomUUID();
        CustomerDTO customerDTO = createCustomerDTO("Mark", "Richards", "markrichards@example.com");
        Customer existingCustomer = createCustomer("Viktor", "Rentea", "viktorentea@example.com");
        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
        Customer updatedCustomer = createCustomer("Mark", "Richards", "markrichards@example.com");
        updatedCustomer.setId(id);
        when(customerMapper.toEntity(customerDTO)).thenReturn(updatedCustomer);
        when(customerMapper.toDto(updatedCustomer)).thenReturn(customerDTO);
        when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

        // When
        CustomerDTO result = customerService.update(id, customerDTO);

        // Then
        assertThat(result.getEmail()).isEqualTo(customerDTO.getEmail());
        verify(customerRepository).findById(id);
        verify(customerMapper).toEntity(customerDTO);
        verify(customerRepository).save(updatedCustomer);
        verify(customerMapper).toDto(updatedCustomer);
    }

    @Test
    void testUpdate_whenCustomerDoesNotExist_shouldThrowException() {
        // Given
        UUID id = UUID.randomUUID();
        CustomerDTO customerDTO = createCustomerDTO("Viktor", "Rentea", "viktorentea@example.com");
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.update(id, customerDTO));
        verify(customerRepository).findById(id);
    }

    @Test
    void testDelete_whenCustomerExists_shouldDeleteCustomer() {
        // Given
        UUID id = UUID.randomUUID();
        Customer existingCustomer = createCustomer("Uncle", "Bob", "unclebob@example.com");
        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));

        // When
        customerService.delete(id);

        // Then
        verify(customerRepository).findById(id);
        verify(customerRepository).delete(existingCustomer);
    }

    @Test
    void testDelete_whenCustomerDoesNotExist_shouldThrowException() {
        // Given
        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.delete(id));
        verify(customerRepository).findById(id);
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
