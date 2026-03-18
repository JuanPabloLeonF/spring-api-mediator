package dev.juanleon.spring_api.product.application.commands.post;

import dev.juanleon.spring_api.common.mediator.IRequestHandler;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;
import dev.juanleon.spring_api.product.application.handler.pots.IProductPostHandler;
import org.springframework.stereotype.Component;

@Component
public class SaveProductCommandHandler implements IRequestHandler<SaveProductCommand, ResponseRequest> {

    private final IProductPostHandler iProductPostHandler;

    public SaveProductCommandHandler(IProductPostHandler iProductPostHandler) {
        this.iProductPostHandler = iProductPostHandler;
    }

    @Override
    public ResponseRequest handle(SaveProductCommand request) {
        return iProductPostHandler.save(request.requestProduct());
    }

    @Override
    public Class<SaveProductCommand> getRequestType() {
        return SaveProductCommand.class;
    }
}
