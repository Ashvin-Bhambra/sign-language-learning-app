package app.service;

import app.entity.Option;
import app.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public void saveOption(Option option){
        optionRepository.save(option);
    }

    public List<Option> getAllOptions(){
        return optionRepository.findAll();
    }

    public Option findOptionById(int id){
        return optionRepository.findById(id).get();
    }
}
