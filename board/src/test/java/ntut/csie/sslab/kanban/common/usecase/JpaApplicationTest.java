package ntut.csie.sslab.kanban.common.usecase;

import ntut.csie.sslab.kanban.main.springboot.web.EzKanbanWebMain;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@ComponentScan(basePackages={"ntut.csie.sslab.kanban"}, excludeFilters= {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value= EzKanbanWebMain.class)})
@EntityScan(basePackages={"ntut.csie.sslab.kanban"})
@SpringBootApplication
public abstract class JpaApplicationTest {
}
