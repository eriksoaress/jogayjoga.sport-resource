package jogayjoga.sport;

import org.springframework.data.repository.CrudRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends CrudRepository<SportModel, String>{
    
}
