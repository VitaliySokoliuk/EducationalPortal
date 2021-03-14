package ua.lviv.EduPortal.Services;

import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.Topic;
import ua.lviv.EduPortal.Repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

    public void deleteById(int id) {
        topicRepository.deleteById(id);
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public Optional<Topic> getByName(String name) {
        return topicRepository.findByName(name);
    }

    public Topic findOrSave(String topic) {
        Optional<Topic> maybeTopic = topicRepository.findByName(topic);
        if(maybeTopic.isEmpty()){
            return topicRepository.save(new Topic(topic));
        }else {
            return maybeTopic.get();
        }
    }
}
