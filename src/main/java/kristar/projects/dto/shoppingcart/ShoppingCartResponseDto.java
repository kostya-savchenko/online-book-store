package kristar.projects.dto.shoppingcart;

import java.util.Set;

public record ShoppingCartResponseDto(
        Long id,
        Long userId,
        Set<CartItemResponseDto> cartItems
) {
}
