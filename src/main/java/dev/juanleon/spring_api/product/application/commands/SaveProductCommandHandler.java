package dev.juanleon.spring_api.product.application.commands;

import dev.juanleon.spring_api.common.mediator.IRequestHandler;
import dev.juanleon.spring_api.product.application.dto.ResponseRequest;
import dev.juanleon.spring_api.product.application.handler.pots.IProductSaveHandler;
import org.springframework.stereotype.Component;

@Component
public class SaveProductCommandHandler implements IRequestHandler<SaveProductCommand, ResponseRequest> {

    private final IProductSaveHandler productSaveHandler;

    public SaveProductCommandHandler(IProductSaveHandler productSaveHandler) {
        this.productSaveHandler = productSaveHandler;
    }

    @Override
    public ResponseRequest handle(SaveProductCommand request) {
        return productSaveHandler.save(request.requestProduct());
    }

    @Override
    public Class<SaveProductCommand> getRequestType() {
        return SaveProductCommand.class;
    }
}
