package jogayjoga.sport;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@Tag(name = "Sport", description = "")
public class SportResource implements SportController {
    
    @Autowired
    private SportService sportService;

    @Override
    public ResponseEntity<SportOut> create(SportIn in) {

        try{
            Sport sport = SportParser.to(in);
            sport = sportService.create(sport);
            return ResponseEntity.ok(SportParser.to(sport));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }


    @Override
    public ResponseEntity<SportOut> get(String id) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok(sportService.read(id));
    }


    @Override
    public ResponseEntity<List<SportOut>> readall()    {
        return ResponseEntity.ok(sportService.readAll());
    }
    


}   