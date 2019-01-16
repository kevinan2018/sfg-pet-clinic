package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private static final String VISITS_NEW_FORM_URI = "/owners/{ownerId}/pets/{petId}/visits/new";
    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNER_URI = "redirect:/owners/{ownerId}";
    private static final String ANOTHER_VISIT_DESCRIPTION = "another visit";

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;

    private final UriTemplate visitsUriTemplate = new UriTemplate(VISITS_NEW_FORM_URI);
    private final Map<String, String> uriVariable = new HashMap<>();
    private URI visitsUri;

    @BeforeEach
    void setUp() {
        final Long petId = 1L;
        final Long ownerId = 1L;

        when(petService.findById(anyLong()))
                .thenReturn(
                  Pet.builder().id(petId)
                    .birthDate(LocalDate.of(2019, 01, 01))
                    .name("Benny")
                    .visits(new HashSet<>())
                    .owner(Owner.builder().id(ownerId).lastName("Mary").firstName("Public").build())
                    .petType(PetType.builder().name("Cat").build())
                    .build()
                );

        uriVariable.clear();
        uriVariable.put("ownerId", ownerId.toString());
        uriVariable.put("petId", petId.toString());
        visitsUri = visitsUriTemplate.expand(uriVariable);

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post(visitsUri)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("date", "2019-01-01")
                    .param("description", ANOTHER_VISIT_DESCRIPTION))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNER_URI))
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attribute("visit", instanceOf(Visit.class)))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("pet", hasProperty("visits", hasSize(1))))
                .andExpect(model().attributeHasNoErrors("visit"));


    }
}