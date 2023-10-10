package ku.cs.backendapi.controller;

import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    QueryService service;

    @GetMapping("/restaurant")
    public Respond getRestaurant(@RequestParam String name) {
        return service.getRestaurant(name);
    }
}
