package dev.juanleon.spring_api.product.application.commands.update;

import dev.juanleon.spring_api.common.mediator.IRequestHandler;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;
import dev.juanleon.spring_api.product.application.handler.update.IProductUpdateHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductCommandHandler implements IRequestHandler<UpdateProductCommand, ResponseRequest> {

    private final IProductUpdateHandler iProductUpdateHandler;

    public UpdateProductCommandHandler(IProductUpdateHandler iProductUpdateHandler) {
        this.iProductUpdateHandler = iProductUpdateHandler;
    }

    @Override
    public ResponseRequest handle(UpdateProductCommand request) {
        return this.iProductUpdateHandler.updateById(request.id(), request.requestProduct());
    }

    @Override
    public Class<UpdateProductCommand> getRequestType() {
        return UpdateProductCommand.class;
    }
}
