package jogayjoga.sport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
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


}   