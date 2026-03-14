package dev.juanleon.spring_api.common.mediator;

public interface IRequestHandler<T extends IRequest<R>, R> {
    public R handle(T request);
    public Class<T> getRequestType();
}
