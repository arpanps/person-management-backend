package com.person.rest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.PersonManagementApplication;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@SpringBootTest(classes = PersonManagementApplication.class)
@WebAppConfiguration
public abstract class AbstractControllerTest {

    protected static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ClassLoader classLoader = AbstractControllerTest.class.getClassLoader();

    public <T> T readResource(final String resourceLocation, final Class<T> clazz) throws Exception {
        return objectMapper.readValue(getResourceContents(resourceLocation), clazz);
    }

    public <T> T readResourceCollection(final String resourceLocation, final TypeReference<T> typeReference)
            throws Exception {
        return objectMapper.readValue(getResourceContents(resourceLocation), typeReference);
    }

    private byte[] getResourceContents(final String resourceLocation) throws Exception {
        final Path filePath = Paths.get(classLoader.getResource(resourceLocation).toURI());
        return Files.readAllBytes(filePath);
    }

    // protected String mapToJson(final Object obj) throws JsonProcessingException {
    // final ObjectMapper objectMapper = new ObjectMapper();
    // return objectMapper.writeValueAsString(obj);
    // }
    //
    // protected <T> T mapFromJson(final String json, final Class<T> clazz)
    // throws JsonParseException, JsonMappingException, IOException {
    //
    // final ObjectMapper objectMapper = new ObjectMapper();
    // return objectMapper.readValue(json, clazz);
    // }

    public static final <T> Set<T> newHashSet(T... objs) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, objs);
        return set;
    }
}
