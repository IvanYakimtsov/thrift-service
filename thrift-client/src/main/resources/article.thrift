namespace java by.bsuir.iit.aipos.thrift

struct Author {
    1 : string email
    2 : string firstName
    3 : string lastName
    4 : list<Article> articleList
}

struct Content {
    1 : string name
    2 : string body
    3 : binary image
    4 : string imageFormat
}

struct Article {
    1 : Content content
    2 : Author author
}

exception ServiceServerException {
    1: string message;
}

service WebPatternsService {
    bool signIn(1 : string email, 2 : string password),
    bool signUp(1 : Author author),
    bool addArticle(1 : string authorEmail, 2 : Content content) throws (1 : ServiceServerException e),
    bool removeArticle(1 : string authorEmail, 2 : string articleName) throws (1 : ServiceServerException e),
    Content getArticle(1 : string articleName) throws (1 : ServiceServerException e),
    bool updateArticle(1 : string authorEmail, 2 : string articleName, 3 : Content modifyContent) throws (1 : ServiceServerException e),
    list<Article> getArticleList() throws (1 : ServiceServerException e)
}