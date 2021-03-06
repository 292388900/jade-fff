package com.recruiters.web.controller.employer;

import com.recruiters.model.Bid;
import com.recruiters.model.User;
import com.recruiters.service.*;
import com.recruiters.service.exception.NotAffiliatedException;
import com.recruiters.service.exception.NotFoundException;
import com.recruiters.service.exception.ServiceException;
import com.recruiters.web.controller.utils.UserUtils;
import com.recruiters.web.helper.UrlResolver;
import com.recruiters.web.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * View bid information and do all bid actions for employer
 */
@Controller
public class EmployerBid {

    /** Employer Service provides all Employer related methods */
    @Autowired
    private EmployerService employerService = null;
    /** User utils for obtaining any session user information */
    @Autowired
    private UserUtils userUtils = null;
    /** Url Builder */
    @Autowired
    private UrlResolver urlResolver;
    /** Json converter service */
    @Autowired
    private JsonService jsonService;

    /**
     * Shows bid by id
     * @param request           Http Request
     * @param response          Http Response
     * @param bidId             Bid id
     * @return json type bid description
     * Internal Server Error page if something is wrong with obtaining data
     * due to technical or any other reasons
     * @throws Exception in very rare circumstances: it should be runtime
     * or servlet Exception to be thrown
     */
    @RequestMapping(value = "/employer-recruiter-show.json", method = RequestMethod.GET)
    public Map<String, Map<String,String>> ajaxRecruiterShow(
            final HttpServletRequest request,
            final HttpServletResponse response,
            @RequestParam(value="bidId", required = true) final Long bidId
    ) throws Exception {
        try {
            User user = userUtils.getCurrentUser(request);
            Bid bid = employerService.findBid(bidId, user.getEmployerId());
            Locale locale = RequestContextUtils.getLocale(request);
            return jsonService.employerShowBid(bid, locale);
        } catch (ServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }


    /**
     * Approve recruiter application
     * @param bidId       Id of application bid
     * @param request     Http Request
     * @param response    Http Response
     * @return json object with success message if anything goes fine,
     * Forbidden page if this bid does not belong to this employer,
     * Internal Server Error page if something is wrong with obtaining data
     * due to technical or any other reasons
     * @throws Exception in very rare circumstances: it should be runtime
     * or servlet Exception to be thrown
     */
    @RequestMapping(value = "/employer-recruiter-approve.json", method = RequestMethod.GET)
    public Object[] approveRecruiterBid(
            @RequestParam(value = "bidId", required = true) final Long bidId,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws Exception {
        try {
            User user = userUtils.getCurrentUser(request);
            employerService.approveBidForRecruiter(bidId, user.getEmployerId());
        } catch (NotAffiliatedException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (ServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
        Locale locale = RequestContextUtils.getLocale(request);

        return jsonService.employerApproveBid(locale);
    }

    /**
     * Decline recruiter application
     * @param bidId       Id of application bid
     * @param request     Http Request
     * @param response    Http Response
     * @return json object with success message if anything goes fine,
     * Forbidden page if this bid does not belong to this employer,
     * Internal Server Error page if something is wrong with obtaining data
     * due to technical or any other reasons
     * @throws Exception in very rare circumstances: it should be runtime
     * or servlet Exception to be thrown
     */
    @RequestMapping(value = "/employer-recruiter-decline.json", method = RequestMethod.GET)
    public Object[] declineRecruiterBid(
            @RequestParam(value = "bidId", required = true) final Long bidId,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws Exception {
        try {
            User user = userUtils.getCurrentUser(request);
            employerService.declineBidForRecruiter(bidId, user.getEmployerId());
        } catch (NotAffiliatedException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (ServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
        Locale locale = RequestContextUtils.getLocale(request);

        return jsonService.employerDeclineBid(locale);
    }

    public EmployerService getEmployerService() {
        return employerService;
    }

    public void setEmployerService(final EmployerService employerService) {
        this.employerService = employerService;
    }
}
