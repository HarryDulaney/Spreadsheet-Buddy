package service;

/**
 * @param <T> Parameter for the primary resource on which the service depends.
 */
public class AbstractFileService<T> extends ParentService {

    private T resource;


    public AbstractFileService(T resource) {
        this.resource = resource;
    }

}
