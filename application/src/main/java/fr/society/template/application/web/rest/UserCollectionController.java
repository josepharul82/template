package fr.society.template.application.web.rest;

import fr.society.template.dto.SearchUserDTO;
import fr.society.template.dto.UserDTO;
import fr.society.template.service.api.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users", name = "UserCollectionController", produces = MediaType.APPLICATION_JSON_VALUE)
@Log
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCollectionController {

    UserService userService;


    @Autowired
    public UserCollectionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "list")
    public ResponseEntity<Page<UserDTO>> list(@RequestBody SearchUserDTO searchUserDTO) {
        Page<UserDTO> users = userService.findByCriteria(searchUserDTO);
        return ResponseEntity.ok(users);
    }

    @PostMapping(value = "create",name = "/create")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        if (userDTO.getId() != null || userDTO.getVersion() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        userService.save(userDTO);
        return ResponseEntity.ok().build();
    }

    //TODO
    /*
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<UserDTO> userDTOList) {
        userService.save(userDTOList);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<UserDTO> userDTOList) {
        userService.save(userDTOList);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        userService.delete(ids);
        return ResponseEntity.ok().build();
    }
    */
}
