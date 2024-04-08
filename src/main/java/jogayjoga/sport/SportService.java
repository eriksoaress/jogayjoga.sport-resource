package jogayjoga.sport;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public Sport create(Sport in){
        return sportRepository.save(new SportModel(in)).to();
    }

    public SportOut read(@NonNull String id) {
        return SportParser.to(sportRepository.findById(id).map(model -> model.to()).orElse(null));
    }
    

}