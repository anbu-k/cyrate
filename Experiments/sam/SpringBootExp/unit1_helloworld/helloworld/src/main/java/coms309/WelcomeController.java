package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to COMS 309";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }

    /**
     * Can return HTML
     */
    @GetMapping("/expierement")
    public String expr()
    {
        return "<h1>Header?<h1>";
    }

    /**
     * Return a HTML file as response
     * doesn't work ATM
     */
    @GetMapping("/model")
    public ModelAndView modelView()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("sample.html");
        return model;
    }
    
}


