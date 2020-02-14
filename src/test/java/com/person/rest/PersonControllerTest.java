package com.person.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.person.execption.PersonNotFoundException;
import com.person.models.Person;
import com.person.rest.advice.PersonControllerAdvice;
import com.person.service.PersonService;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class PersonControllerTest extends AbstractControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;
    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    // public void setUp() throws Exception {
    // Person firstPerson = new Person(1L, "fn1", "ln", 25, "red", newHashSet("a", "b", "c"));
    // Person secondPerson = new Person(2L, "fn2", "ln", 25, "red", newHashSet("a", "b", "c"));
    //
    // final Person updatedPerson = new Person(1L, "First Name", "ln", 25, "red", newHashSet("a", "b", "c"));
    //
    // Mockito.when(personService.getPerson(1L)).thenReturn(firstPerson);
    // Mockito.when(personService.getPerson(10L)).thenThrow(new PersonNotFoundException("Could not find person 10"));
    //
    // Mockito.when(personService.getPersons()).thenReturn(Arrays.asList(firstPerson, secondPerson));
    //
    // Mockito.when(personService.createPerson(firstPerson)).thenReturn(firstPerson);
    //
    // Mockito.when(personService.updatePerson(updatedPerson)).thenReturn(updatedPerson);
    //
    // mockMvc = MockMvcBuilders.standaloneSetup(personController).setControllerAdvice(new PersonControllerAdvice())
    // .build();
    // }
    //
    // public void mock() {
    // this.mockMvc = MockMvcBuilders.standaloneSetup(personController)
    // .setControllerAdvice(new PersonControllerAdvice()).build();
    // }

    @Test
    public void testShouldReturnListOfPersons() throws Exception {
        Person firstPerson = new Person(1L, "fn1", "ln", 25, "red", newHashSet("a", "b", "c"));
        Person secondPerson = new Person(2L, "fn2", "ln", 25, "red", newHashSet("a", "b", "c"));

        when(personService.getPersons()).thenReturn(Arrays.asList(firstPerson, secondPerson));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setControllerAdvice(new PersonControllerAdvice()).build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/list").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].first_name").value("fn1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].first_name").value("fn2"));

        Mockito.verify(personService, Mockito.times(1)).getPersons();
    }

    @Test
    public void testShouldReturnSelectedPerson() throws Exception {
        Person firstPerson = new Person(1L, "fn1", "ln", 25, "red", newHashSet("a", "b", "c"));
        when(personService.getPerson(1L)).thenReturn(firstPerson);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setControllerAdvice(new PersonControllerAdvice()).build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/1").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("fn1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(25))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favourite_colour").value("red"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hobby").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hobby.[0]").value("a"));

        Mockito.verify(personService, Mockito.times(1)).getPerson(1L);
    }

    @Test
    public void testShouldReturnErrorMessageIfPersonNotFound() throws Exception {
        when(personService.getPerson(10L)).thenThrow(new PersonNotFoundException("Person 10 not found"));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setControllerAdvice(new PersonControllerAdvice()).build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/10").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andDo(print()).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Person 10 not found"));
    }

    // @Test
    // public void testShouldSavePerson() throws Exception {
    // final Person personRequest = readResource("fixtures/mock-person-creation.json", Person.class);
    //
    // final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/persons")
    // .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(personRequest));
    //
    // this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated())
    // .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/persons/1"))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("fn1"))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(25))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.favourite_colour").value("red"))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.hobby").isArray())
    // .andExpect(MockMvcResultMatchers.jsonPath("$.hobby.[0]").value("a"));
    //
    // Person person = new Person(1L, "fn", "ln", 25, "red", newHashSet("a", "b", "c"));
    // Mockito.verify(personService, Mockito.times(1)).createPerson(person);
    // }
    //
    // @Test
    // public void testShouldUpdatePerson() throws Exception {
    // final Person personRequest = readResource("fixtures/mock-person-update.json", Person.class);
    //
    // final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/persons/1")
    // .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(personRequest));
    //
    // this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
    // .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Person"))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Test Description"))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
    // .andExpect(MockMvcResultMatchers.jsonPath("$.products.[0].id").value("testPersonId"));
    //
    // Person person = new Person(1L, "fn", "ln", 25, "red", newHashSet("a", "b", "c"));
    // Mockito.verify(personService, Mockito.times(1)).updatePerson(person);
    // }

    // @Test
    // public void testShouldDeletePerson() throws Exception {
    // Person firstPerson = new Person(1L, "fn1", "ln", 25, "red", newHashSet("a", "b", "c"));
    // Mockito.when(personService.getPerson(1L)).thenReturn(firstPerson);
    // mock();
    //
    // this.mockMvc.perform(MockMvcRequestBuilders.delete("/person/1"));
    // Mockito.verify(personService, Mockito.times(1)).deletePerson(1L);
    // }
}
