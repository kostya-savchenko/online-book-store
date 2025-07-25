package kristar.projects.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import kristar.projects.dto.order.CreateOrderRequestDto;
import kristar.projects.dto.order.OrderDto;
import kristar.projects.dto.order.OrderItemResponseDto;
import kristar.projects.dto.order.UpdateOrderStatusRequestDto;
import kristar.projects.model.StatusName;
import kristar.projects.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Place an order",
            description = "Placement an order from shoppingCart of user")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid CreateOrderRequestDto requestDto) {
        return orderService.save(requestDto);
    }

    @Operation(summary = "Get all orders of current user",
            description = "Getting all orders of current user")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<OrderDto> getOrdersOfCurrentUser() {
        return orderService.findAllOrdersByCurrentUser();
    }

    @Operation(summary = "Change Status of order (admin only)",
            description = "Change Status of order")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public OrderDto updateOrderStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateOrderStatusRequestDto requestDto
    ) {
        return orderService.updateOrder(id, requestDto);
    }

    @Operation(summary = "Get all orderItems of order",
            description = "Get all orderItems of order")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}/items")
    public List<OrderItemResponseDto> getAllOrderItems(@PathVariable Long orderId) {
        return orderService.findAllItemsByOrderId(orderId);
    }

    @Operation(summary = "Get one specific item by itemId from the order by orderId",
            description = "Get one specific item by itemId from the order by orderId")
    @GetMapping("/{orderId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public OrderItemResponseDto getOrderItemById(
            @PathVariable Long orderId,
            @PathVariable Long itemId
    ) {
        return orderService.findItemByOrderIdItemId(orderId, itemId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/status/{status}")
    public List<OrderDto> getOrdersByStatus(@PathVariable StatusName status) {

        return orderService.findAllOrdersByStatus(status);
    }
}
