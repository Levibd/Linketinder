package filter

import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter("/*")
class CorsFilter implements Filter{

    @Override
    void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        def res = response as HttpServletResponse
        def req = request as HttpServletRequest


        res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200")


        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")


        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")


        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK)
            return
        }

        chain.doFilter(request, response)
    }

    @Override
    void destroy() {

    }
}
