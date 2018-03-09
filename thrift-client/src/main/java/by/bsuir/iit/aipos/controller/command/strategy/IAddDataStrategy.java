package by.bsuir.iit.aipos.controller.command.strategy;

import by.bsuir.iit.aipos.service.excpetion.BodyFieldException;
import by.bsuir.iit.aipos.service.excpetion.NameFieldException;
import by.bsuir.iit.aipos.thrift.Article;

public interface IAddDataStrategy {
    void execute(Article article) throws NameFieldException, BodyFieldException;
}
