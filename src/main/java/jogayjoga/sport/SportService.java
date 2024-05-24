package jogayjoga.sport;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public Sport create(Sport in){
        return sportRepository.save(new SportModel(in)).to();
    }

    @Cacheable("sport")
    public SportOut read(@NonNull String id) {
        return SportParser.to(sportRepository.findById(id).map(model -> model.to()).orElse(null));
    }

    public List<SportOut> readAll() {
        List<SportModel> sportModels = new ArrayList<>();
        sportRepository.findAll().forEach(sportModels::add);

        List<Sport> sports = sportModels.stream()
            .map(SportModel::to)
            .collect(Collectors.toList());

        if (sports.isEmpty()) {
            return null;
        }

        // converte sports para List<SportOut>
        List<SportOut> SportOuts = new ArrayList<>();
        for (Sport sport : sports) {
            SportOuts.add(SportParser.to(sport));
        }

        return SportOuts;
    }
    

}