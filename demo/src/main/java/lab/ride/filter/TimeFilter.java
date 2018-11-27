package lab.ride.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 只能收到request和response， 不知道被哪个方法处理
 * @author cwz
 * @date 2018/11/26
 */
//@Component
public class TimeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("time filter: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destory");
    }
}
