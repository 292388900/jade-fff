package com.recruiters.web.controller.employer;

import com.recruiters.model.Applicant;
import com.recruiters.model.User;
import com.recruiters.service.EmployerService;
import com.recruiters.service.exception.NotAffiliatedException;
import com.recruiters.service.exception.NotFoundException;
import com.recruiters.service.exception.ServiceException;
import com.recruiters.web.controller.utils.UserUtils;
import com.recruiters.web.helper.UrlResolver;
import com.recruiters.web.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

/**
 * Applicant view and actions for employer
 */
@Controller
public class EmployerApplicantView {

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
     * Show applicant details in json format
     * @param applicantId    Id of applicant
     * @param request        Http request
     * @param response       Http response
     * @return json with applicant
     * Forbidden page if requested applicant is not related to any vacancy
     * of current employer,
     * Not Found page if there is no applicant with such id,
     * Internal Server Error page if something is wrong with obtaining data
     * due to technical or any other reasons
     * @throws Exception in very rare circumstances: it should be runtime
     * or servlet Exception to be thrown
     */
    @RequestMapping(value = "/employer-applicant-show.json", method = RequestMethod.GET)
    public Map<String,Map<String,String>> applicantShow(
            @RequestParam(value = "applicantId", required = true) final Long applicantId,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws Exception {
        try {
            User user = userUtils.getCurrentUser(request);
            Applicant applicant = employerService.findApplicant(applicantId, user.getEmployerId());
            Locale locale = RequestContextUtils.getLocale(request);

            return jsonService.employerShowApplicant(applicant, locale);
        } catch (NotAffiliatedException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (ServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    /**
     * Approve applicant
     * @param applicantId    Id of applicant
     * @param request        Http request
     * @param response       Http response
     * @return json type successful message,
     * Forbidden page if this applicant is not related to any vacancy
     * of current employer,
     * Internal Server Error page if something is wrong with obtaining data
     * due to technical or any other reasons
     * @throws Exception in very rare circumstances: it should be runtime
     * or servlet Exception to be thrown
     */
    @RequestMapping(value = "/employer-applicant-approve.json", method = RequestMethod.GET)
    public Object[] applicantApply(
            @RequestParam(value = "applicantId", required = true) final Long applicantId,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws Exception {
        try {
            User user = userUtils.getCurrentUser(request);
            employerService.applyApplicant(applicantId, user.getEmployerId());
        } catch (NotAffiliatedException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (ServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
        Locale locale = RequestContextUtils.getLocale(request);

        return jsonService.employerApplyApplicant(locale);
    }


    /**
     * Decline applicant
     * @param applicantId    Id of applicant
     * @param request        Http request
     * @param response       Http response
     * @return json type successful message,
     * Forbidden page if this applicant is not related to any vacancy
     * of current employer,
     * Internal Server Error page if something is wrong with obtaining data
     * due to technical or any other reasons
     * @throws Exception in very rare circumstances: it should be runtime
     * or servlet Exception to be thrown
     */
    @RequestMapping(value = "/employer-applicant-decline.json", method = RequestMethod.GET)
    public Object[] applicantDecline(
            @RequestParam(value = "applicantId", required = true) final Long applicantId,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws Exception {
        try {
            User user = userUtils.getCurrentUser(request);
            employerService.declineApplicant(applicantId, user.getEmployerId());
            Locale locale = RequestContextUtils.getLocale(request);

            return jsonService.employerDeclineApplicant(locale);
        } catch (NotAffiliatedException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (ServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    public EmployerService getEmployerService() {
        return employerService;
    }

    public void setEmployerService(final EmployerService employerService) {
        this.employerService = employerService;
    }
}
