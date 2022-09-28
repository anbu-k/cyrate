package onetomany.Phones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneController {

    @Autowired
    PhoneRepository phoneRepository;
    private String loginSuccess = "{\"message\":\"success\"}";
    private String loginFailure = "{\"message\":\"failure\"}";

    /*
     * private string loginSuccess = "{\"message\":\"success\"}";
     * private string loginFailure = "{\"message\":\"success\"}";
     */

    @GetMapping(path = "/phones")
    List<Phone> getAllPhones(){
        return phoneRepository.findAll();
    }

    @GetMapping(path = "/phones/{id}")
    Phone getPhoneById( @PathVariable int id){
        return phoneRepository.findById(id);
    }

    @PostMapping(path = "/phones")
    String createPhone(Phone phone){
        if (phone == null)
            return loginFailure;
        phoneRepository.save(phone);
        return loginSuccess;
    }

    @PutMapping("/phones/{id}")
    Phone updatePhone(@PathVariable int id, @RequestBody Phone request){
        Phone phone = phoneRepository.findById(id);
        if(phone == null)
            return null;
        phoneRepository.save(request);
        return phoneRepository.findById(id);
    } 
      
}
