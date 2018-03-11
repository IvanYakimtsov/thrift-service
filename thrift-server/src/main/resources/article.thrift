namespace java by.bsuir.iit.aipos.thrift

struct Article {
    1 : string name
    2 : binary image
    3 : string imageFormat
    4 : string body
}

exception ServiceServerException {
    1: string message;
}

service WebPatternsService {
    bool addArticle(1 : Article article) throws (1 : ServiceServerException e),
    bool removeArticle(1 : string articleName) throws (1 : ServiceServerException e),
    Article getArticle(1 : string articleName) throws (1 : ServiceServerException e),
    bool update(1: string articleName, 2 : Article article) throws (1 : ServiceServerException e),
    list<string> getArticleList() throws (1 : ServiceServerException e)
}