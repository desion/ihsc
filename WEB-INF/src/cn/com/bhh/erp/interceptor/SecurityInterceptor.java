package cn.com.bhh.erp.interceptor;

import java.util.Map;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.action.UserAction;
import cn.com.bhh.erp.entity.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * if user havn't been login, show the login page.
 * @author fenghy
 *
 */
@SuppressWarnings("serial")
public class SecurityInterceptor implements Interceptor {
    @SuppressWarnings("unchecked")
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String name = invocation.getInvocationContext().getName();
       
       if ("".equals(name) || "BS008_00".equals(name) || "BS008_01".equals(name) || "BS008_02".equals(name)) {
            // if the user want to login , let go
            return invocation.invoke();
        } else {
            Map session = invocation.getInvocationContext().getSession();
            if (session == null) {
                return Action.LOGIN;
            } else {
                User user = (User) session.get(UserAction.USER);

                if (user == null) {
                    // if there is no user data in session, means session is out
                    // of time login again.
                    return Action.LOGIN;
                } else {
                    if (user.hasPermission(name)) {
                        // Inject loginUser
                        Object action = invocation.getAction();

                        if (action instanceof BaseAction) {
                            ((BaseAction) action).setLoginUser(user);
                        }

                        return invocation.invoke();
                    } else {
                        return Action.LOGIN;
                    }
                }
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }
}
