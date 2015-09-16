package meal.rank.app.controllers;


import meal.rank.app.dto.MealDTO;
import meal.rank.app.dto.NewUserDTO;
import meal.rank.app.dto.UserInfoDTO;
import meal.rank.app.model.Group;
import meal.rank.app.model.User;
import meal.rank.app.services.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 *
 *  REST service for users.
 *
 */

@Controller
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value="/api/user", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public UserInfoDTO getUserInfo(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        return user != null ? new UserInfoDTO(user.getUsername(), user.getNickname(), user.getGroup().getName()) : null;
    }

    @RequestMapping(value="/api/groupmembers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<UserInfoDTO> getGroupUsers(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        List<User> users = userService.findUserByGroupname(user.getGroup().getName());
        return UserInfoDTO.mapFromUsersEntities(users);
     }
    
    
    @RequestMapping(value="/api/user", method = RequestMethod.POST)
    public ResponseEntity<String>  createUser(@RequestBody NewUserDTO user) {
        userService.createUser(user.getUsername(), user.getEmail(), user.getPlainTextPassword());
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
