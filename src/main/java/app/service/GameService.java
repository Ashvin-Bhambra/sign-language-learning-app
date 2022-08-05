package app.service;


import app.entity.Game;
import app.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game getGameByGameId(int id){
        return gameRepository.findById(id).get();
    }

    public void saveGame(Game game){
        this.gameRepository.save(game);
    }
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
}
