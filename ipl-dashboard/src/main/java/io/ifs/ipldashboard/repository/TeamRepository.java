package io.ifs.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import io.ifs.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team,String>{
    
    Team findByTeamName(String teamName);
}
