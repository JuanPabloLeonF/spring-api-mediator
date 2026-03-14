package dev.juanleon.spring_api.common.mediator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mediator {

    Map<? extends Class<?>, IRequestHandler<?, ?>> requestHandlerMap;

    public Mediator(List<IRequestHandler<?, ?>> iRequestHandlers) {
        this.requestHandlerMap = iRequestHandlers.stream()
                .collect(Collectors.toMap(IRequestHandler::getRequestType, Function.identity()));
    }

    public <R, T extends IRequest<R>> R dispatch(T request) {
        IRequestHandler<T, R> handler = (IRequestHandler<T, R>) this.requestHandlerMap.get(request.getClass());
        if (handler == null) {
            throw new RuntimeException("No handler found for request type: " + request.getClass().getName());
        }
        return handler.handle(request);
    }
}
