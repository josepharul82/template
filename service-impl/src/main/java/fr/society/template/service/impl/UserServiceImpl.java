package fr.society.template.service.impl;

import fr.society.template.dto.SearchUserDTO;
import fr.society.template.dto.UserDTO;
import fr.society.template.model.User;
import fr.society.template.repository.users.UserRepository;
import fr.society.template.service.api.UserService;
import fr.society.template.service.mapper.UserMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@Log
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.dtoToEntity(userDTO);
        return userMapper.entityToDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> findAll(Iterable<Long> userIds) {
        return userMapper.entityToDTO(userRepository.findAllById(userIds));
    }

    public UserDTO findById(Long userId) {
        return userMapper.entityToDTO(userRepository.findById(userId).orElse(null));
    }

    @Override
    public Page<UserDTO> findByCriteria(SearchUserDTO searchUserDTO) {
        Page<User> userPage = userRepository.findByCriteria(searchUserDTO);
        List<UserDTO> userDTOList = userMapper.entityToDTO(userPage.getContent());
        return new PageImpl<UserDTO>(userDTOList,userPage.getPageable(),userPage.getTotalElements());

    }
}
