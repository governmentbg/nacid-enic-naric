package bg.duosoft.nacidshareddata.validation.config;

public interface ExceptionValidator<T> extends DefaultValidation {
    void validate(T obj, Object... args);
}
