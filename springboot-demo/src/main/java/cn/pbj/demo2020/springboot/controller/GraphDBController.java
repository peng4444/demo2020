package cn.pbj.demo2020.springboot.controller;

import cn.pbj.demo2020.springboot.service.RoutingDelegate;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @pClassName: GraphDBController
 * @author: pengbingjiang
 * @create: 2020/11/19 10:42
 * @description: TODO /graphdb/** 转发到 Graph_Server/**
 */
@RestController
@RequestMapping(GraphDBController.DELEGATE_PREFIX)
@Api(value = "GraphDB", tags = {
        "graphdb-Api"
})
public class GraphDBController {

    //@Autowired
    //GraphProperties graphProperties;

    public final static String DELEGATE_PREFIX = "/graphdb";

    @Autowired
    private RoutingDelegate routingDelegate;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity catchAll(HttpServletRequest request, HttpServletResponse response) {
        //return routingDelegate.redirect(request, response, graphProperties.getGraphServer(), DELEGATE_PREFIX);
        return routingDelegate.redirect(request, response, "routeUrl=graphdb-Api", DELEGATE_PREFIX);
    }
}
