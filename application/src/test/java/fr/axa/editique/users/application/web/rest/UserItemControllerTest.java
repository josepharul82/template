package fr.axa.editique.users.application.web.rest;

import fr.axa.editique.users.dto.UserDTO;
import fr.axa.editique.users.service.api.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(UserItemController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Before
    public void init(){
        initUserServiceForFindById();

    }

    private void initUserServiceForFindById() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(Long.valueOf(1));
        Mockito.doReturn(userDTO).when(userService).findById(Long.valueOf(1));
    }

    @Test
    public void findByIdShouldReturnUserDTO() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{userId}","1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }
}
