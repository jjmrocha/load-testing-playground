package net.uiqui.load.apps.control;

import net.uiqui.load.apps.entity.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationStore extends CrudRepository<Application, Integer> {
}
