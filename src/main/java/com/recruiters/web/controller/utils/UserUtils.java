package com.recruiters.web.controller.utils;

import com.recruiters.model.User;
import com.recruiters.repository.UserRepository;
import com.recruiters.service.*;
import com.recruiters.service.SecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Service Class for Employer
 */
@Service
public class UserUtils {

    @Autowired
    private UserRepository userRepository = null;

    /**
     * Finds user by session info
     * @param request Http request
     * @return User POJO instance
     */
    public User getCurrentUser(final HttpServletRequest request) throws ServiceException {
        User user =  (User) request.getSession().getAttribute("currentUser");
        if (user == null) {
            throw new ServiceException("User is null");
        }

        return user;
    }
}
