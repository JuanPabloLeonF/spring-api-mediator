package dev.juanleon.spring_api.product.application.commands.delete;

import dev.juanleon.spring_api.common.mediator.IRequestHandler;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;
import dev.juanleon.spring_api.product.application.handler.delete.IProductDeleteHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCommandHandler implements IRequestHandler<DeleteProductCommand, ResponseRequest> {

    private final IProductDeleteHandler iProductDeleteHandler;

    public DeleteProductCommandHandler(IProductDeleteHandler iProductDeleteHandler) {
        this.iProductDeleteHandler = iProductDeleteHandler;
    }

    @Override
    public ResponseRequest handle(DeleteProductCommand request) {
        return this.iProductDeleteHandler.deleteById(request.id());
    }

    @Override
    public Class<DeleteProductCommand> getRequestType() {
        return DeleteProductCommand.class;
    }
}
