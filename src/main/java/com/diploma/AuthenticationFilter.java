package com.diploma;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.diploma.DAO.UsersEntityImpl;
import com.diploma.Entities.UsersEntity;
import org.glassfish.jersey.internal.util.Base64;

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
    @Inject
    private UsersEntityImpl userDao;

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private HttpServletRequest req;

//    private static final String AUTHORIZATION_PROPERTY = "Authorization";
//    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        Method method = resourceInfo.getResourceMethod();
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Access blocked for all users !!").build());
                return;
            }

//            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
//
//            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
//
//            if(authorization == null || authorization.isEmpty())
//            {
//                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//                return;
//            }
//
//            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
//
//            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));;
//
//            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
//            final String username = tokenizer.nextToken();
//            final String password = tokenizer.nextToken();

            HttpSession sess = req.getSession();
            final String username;
            final String password;

            try {
                username = sess.getAttribute("login").toString();
                password = sess.getAttribute("pass").toString();
            } catch (Exception e)
            {
                throw e;
            }
            UsersEntity user = new UsersEntity();
            user.setUname(username);
            user.setUpassword(password);

            System.out.println(username);
            System.out.println(password);

            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                if( ! isUserAllowed(user, rolesSet))
                {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    return;
                }
            }
        }
    }
    private boolean isUserAllowed(final UsersEntity user, final Set<String> rolesSet)
    {
        boolean isAllowed = false;

        if(userDao.validateUser(user))
        {
            String userRole = "ADMIN";

            if(rolesSet.contains(userRole))
            {
                isAllowed = true;
            }
        }
        return isAllowed;
    }
}